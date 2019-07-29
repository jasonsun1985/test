package com.tec.springel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/** 
 * @Description:
 * <p>创建日期：2019年3月14日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class TakeBus {
	public static void main(String[] args) {
		People people1 = new People("张三",61,"男");
		People people2 = new People("李四",59,"女");
		Bus bus = new Bus("911",2);
		bus.setPeople(people1);
		EvaluationContext context = new StandardEvaluationContext();  // 表达式的上下文,
		context.setVariable("p", people1);
		context.setVariable("b", bus);
		ExpressionParser  parser = new SpelExpressionParser();
		System.out.println(parser.parseExpression("#p.getAge()").getValue(context,Integer.class));
		System.out.println(parser.parseExpression("#b.getMoney()").getValue(context,Integer.class));
		
		
	}
}
