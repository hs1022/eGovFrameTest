package egovframework.lab.aop.xml;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import egovframework.lab.aop.common.BizException;

public class AdviceUsingXML {

	private final Log sysoutLogger = LogFactory.getLog(AdviceUsingXML.class);

	public void beforeTargetMethod(JoinPoint thisJoinPoint) {
		sysoutLogger.debug("\nAdviceUsingXML.beforeTargetMethod executed.");
		@SuppressWarnings("unchecked")
		Class clazz = thisJoinPoint.getTarget().getClass();
		String className = thisJoinPoint.getTarget().getClass().getSimpleName();
		String methodName = thisJoinPoint.getSignature().getName();

		// 현재 class, method 정보 및 method arguments 로깅
		StringBuffer buf = new StringBuffer();
		buf.append("\n== AdviceUsingXML.beforeTargetMethod : [" + className + "." + methodName + "()] ==");
		Object[] arguments = thisJoinPoint.getArgs();
		int argCount = 0;
		for (Object obj : arguments) {
			buf.append("\n - arg ");
			buf.append(argCount++);
			buf.append(" : ");
			// commons-lang 의 ToStringBuilder 를
			// 통해(reflection 을 이용)한 VO 정보 출력
			buf.append(ToStringBuilder.reflectionToString(obj));
		}

		// 대상 클래스의 logger 를 사용하여 method arguments 로깅하였음.
		Log logger = LogFactory.getLog(clazz);
		if (logger.isDebugEnabled())
			logger.debug(buf.toString());
	}

	public void afterTargetMethod(JoinPoint thisJoinPoint) {
		sysoutLogger.debug("AdviceUsingXML.afterTargetMethod executed.");
	}

	@SuppressWarnings("unchecked")
	public void afterReturningTargetMethod(JoinPoint thisJoinPoint, Object retVal) {
		sysoutLogger.debug("AdviceUsingXML.afterReturningTargetMethod executed.");
		Class clazz = thisJoinPoint.getTarget().getClass();
		String className = thisJoinPoint.getTarget().getClass().getSimpleName();
		String methodName = thisJoinPoint.getSignature().getName();

		// 현재 class, method 정보및 method arguments 로깅
		StringBuffer buf = new StringBuffer();
		buf.append("\n== AdviceUsingXML.afterReturningTargetMethod : [" + className + "." + methodName + "()] ==");
		buf.append("\n");

		// 결과값이 List 이면 size와 전체 List 데이터를 풀어 reflection 으로 출력- 성능상 사용않는 것이 좋음
		if (retVal instanceof List) {
			List resultList = (List) retVal;
			buf.append("resultList size : " + resultList.size() + "\n");
			for (Object oneRow : resultList) {
				buf.append(ToStringBuilder.reflectionToString(oneRow));
				buf.append("\n");
			}
		} else {
			buf.append(ToStringBuilder.reflectionToString(retVal));
		}

		// 대상 클래스의 logger를 사용하여 결과값 로깅하였음.
		Log logger = LogFactory.getLog(clazz);
		if (logger.isDebugEnabled())
			logger.debug(buf.toString());
		// return value의 변경은 불가함에 유의!
	}

	public void afterThrowingTargetMethod(JoinPoint thisJoinPoint, Exception exception) throws Exception {
		sysoutLogger.debug("AdviceUsingXML.afterThrowingTargetMethod executed.");
		sysoutLogger.error("에러가 발생했습니다.", exception);
		// 원본 exception을 wrapping 하고 user-friendly한
		// 메시지를 설정하여 새로운 Exception 으로 re-throw
		throw new BizException("에러가 발생했습니다.", exception);
		// 여기서는 간단하게 작성하였지만 일반적으로 messageSource를 사용한
		// locale에 따른 다국어 처리및 egov. exceptionHandler
		// 를 확장한 Biz. (ex. email 공지등) 기능 적용이 가능함.
	}

	public Object aroundTargetMethod(ProceedingJoinPoint thisJoinPoint) throws Throwable {
		sysoutLogger.debug("AdviceUsingXML.aroundTargetMethod start.");
		long time1 = System.currentTimeMillis();
		Object retVal = thisJoinPoint.proceed();
		// Around advice의 경우 결과값을 변경할 수도 있음!
		// 위의 retVal을 가공하거나 심지어 전혀 다른 결과값을 대체하여 caller에 되돌려줄수 있음
		long time2 = System.currentTimeMillis();
		sysoutLogger.debug("AdviceUsingXML.aroundTargetMethod end. Time(" + (time2 - time1) + ")");
		return retVal;

		// TODO [Step 1-6] AdviceUsingXML 작성
	}
}