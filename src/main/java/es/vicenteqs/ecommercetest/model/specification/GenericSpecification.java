package es.vicenteqs.ecommercetest.model.specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.query.criteria.internal.path.ListAttributeJoin;
import org.springframework.data.jpa.domain.Specification;

import es.vicenteqs.ecommercetest.exception.FilterOperationNotSupportedException;
import es.vicenteqs.ecommercetest.exception.runtime.BadRequestException;
import es.vicenteqs.ecommercetest.model.domain.User;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
public class GenericSpecification<T> implements Specification<T> {

	private static final long serialVersionUID = 1760134961473698448L;

	private String filter;
	private User user;

	public GenericSpecification(String toFilter) {
		this.filter = toFilter;
	}

	public GenericSpecification(String toFilter, User owner) {
		this.filter = toFilter;
		this.user = owner;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		try {
			return this.createCriteria(root, builder, this.filter);
		} catch (Exception ex) {
			GenericSpecification.log.error("filter", ex);
			throw new BadRequestException();
		}
	}

	private Predicate createCriteria(Root<T> root, CriteriaBuilder builder, String filterChunk)
			throws FilterOperationNotSupportedException, ParseException {

		Predicate userPredicate = null;
		Predicate predicate = null;

		if (this.filter != null && !this.filter.isEmpty()) {
			predicate = this.splitFilter(root, builder, filterChunk);
		}

		if (this.user != null) {
			userPredicate = builder.equal(root.get("user"), this.user);
			if (predicate == null) {
				predicate = userPredicate;
			} else {
				predicate = builder.and(predicate, userPredicate);
			}
		}

		return predicate;

	}

	private Predicate splitFilter(Root<T> root, CriteriaBuilder builder, String filterChunk)
			throws FilterOperationNotSupportedException, ParseException {

		Predicate partialPredicate = null;
		Predicate predicate = null;
		StringBuilder condition = new StringBuilder();
		StringBuilder union = new StringBuilder();

		int level = 0;

		for (int i = 0; i < filterChunk.length(); i++) {
			char chr = filterChunk.charAt(i);

			if (chr == '(') {
				level++;
			} else if (chr == ')') {
				level--;
				if (level == 0) {

					partialPredicate = this.calcPartialPredicate(root, builder, condition);
					predicate = this.calcPredicateUnion(builder, union, predicate, partialPredicate);

					condition.setLength(0);
					union.setLength(0);
				}
			}

			if (level > 0) {
				if (level != 1 || chr != '(') {
					condition.append(chr);
				}
			} else if (chr != ')' && chr != ' ') {
				union.append(chr);
			}
		}

		return predicate;
	}

	@SuppressWarnings("static-method")
	private Predicate calcPredicateUnion(CriteriaBuilder builder, StringBuilder union, Predicate predicate,
			Predicate partialPredicate) {

		Predicate predicateResult = null;

		if (union.length() == 0) {
			predicateResult = partialPredicate;
		} else if (union.toString().equalsIgnoreCase("AND")) {
			predicateResult = builder.and(predicate, partialPredicate);
		} else if (union.toString().equalsIgnoreCase("OR")) {
			predicateResult = builder.or(predicate, partialPredicate);
		}

		return predicateResult;
	}

	private Predicate calcPartialPredicate(Root<T> root, CriteriaBuilder builder, StringBuilder condition)
			throws FilterOperationNotSupportedException, ParseException {

		Predicate partialPredicate;

		if (condition.toString().contains("(")) {
			partialPredicate = this.createCriteria(root, builder, condition.toString());
		} else {
			String[] condItems = condition.toString().split(" ");
			String value = condItems[2];

			for (int i = 3; i < condItems.length; i++) {
				value += " " + condItems[i];
			}

			partialPredicate = this.calcValue(root, builder, condItems[0], condItems[1], value);
		}

		return partialPredicate;
	}

	@SuppressWarnings("unchecked")
	private Predicate calcValue(Root<T> root, CriteriaBuilder builder, String fieldName, String operation, String value)
			throws FilterOperationNotSupportedException, ParseException {

		String[] splLevels;
		Path<?> path;

		if (fieldName.contains(".")) {
			splLevels = fieldName.split("\\.");
			path = root;

			for (int i = 0; i < splLevels.length; i++) {
				if (i == splLevels.length - 1) {
					path = path.get(splLevels[i]);
				} else if (path instanceof Root<?>) {
					path = ((Root<T>) path).join(splLevels[i]);
				} else if (path instanceof ListAttributeJoin<?, ?>) {
					path = ((ListAttributeJoin<?, ?>) path).join(splLevels[i]);
				} else {
					path = path.get(splLevels[i]);
				}
			}

		} else {
			path = root.get(fieldName);
		}

		return this.generatePredicate(builder, operation, value, path);

	}

	@SuppressWarnings("unchecked")
	private Predicate generatePredicate(CriteriaBuilder builder, String operation, String value, Path<?> path)
			throws FilterOperationNotSupportedException, ParseException {

		Predicate predicate = null;
		Class<? extends Object> type = path.getJavaType();

		if (type == Integer.class) {
			predicate = this.<Integer>calcCondition((Path<Integer>) path, builder, operation, Integer.valueOf(value));
		} else if (type == String.class) {
			predicate = this.<String>calcCondition((Path<String>) path, builder, operation, value.toUpperCase());
		} else if (type == Long.class) {
			predicate = this.<Long>calcCondition((Path<Long>) path, builder, operation, Long.valueOf(value));
		} else if (type == Float.class) {
			predicate = this.<Float>calcCondition((Path<Float>) path, builder, operation, Float.valueOf(value));
		} else if (type == Double.class) {
			predicate = this.<Double>calcCondition((Path<Double>) path, builder, operation, Double.valueOf(value));
		} else if (type == Boolean.class) {
			predicate = this.<Boolean>calcCondition((Path<Boolean>) path, builder, operation, Boolean.valueOf(value));
		} else if (type == Date.class) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date date = formatter.parse(value);
			predicate = this.<Date>calcCondition((Path<Date>) path, builder, operation, date);
		}

		return predicate;
	}

	@SuppressWarnings({ "static-method", "unchecked" })
	private <V extends Comparable<V>> Predicate calcCondition(Path<V> path, CriteriaBuilder builder, String operation,
			V value) throws FilterOperationNotSupportedException {

		Predicate predicate = null;

		if (operation.equalsIgnoreCase("eq")) {
			predicate = builder.equal(path, value);
		} else if (operation.equalsIgnoreCase("ne")) {
			predicate = builder.notEqual(path, value);
		} else if (operation.equalsIgnoreCase("gte")) {
			predicate = builder.greaterThanOrEqualTo(path, value);
		} else if (operation.equalsIgnoreCase("lte")) {
			predicate = builder.lessThanOrEqualTo(path, value);
		} else if (operation.equalsIgnoreCase("gt")) {
			predicate = builder.greaterThan(path, value);
		} else if (operation.equalsIgnoreCase("lt")) {
			predicate = builder.lessThan(path, value);
		} else if (operation.equalsIgnoreCase("like")) {
			predicate = builder.like(builder.upper((Expression<String>) path), "%" + value + "%");
		} else {
			throw new FilterOperationNotSupportedException();
		}

		return predicate;
	}

}
