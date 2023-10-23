package br.com.postech.software.architecture.techchallenge.enums;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.DynamicParameterizedType;
import org.hibernate.usertype.UserType;
import org.springframework.util.ObjectUtils;

import br.com.postech.software.architecture.techchallenge.util.Constantes;

@SuppressWarnings("rawtypes")
public class AssociacaoType implements UserType<APIEnum>, DynamicParameterizedType {

	private Class<?> enumClass;
    private Method recreateEnumMthd;
    private Method recreateStringMthd;
	private String metodoGetEnum = "get";
	private String metodoGetValue = "getValue";
	
	@Override
	public void setParameterValues(Properties parameters) {
		if (parameters != null) {
			if (StringUtils.isNotEmpty(parameters.getProperty("metodoGetEnum"))) {
				metodoGetEnum = parameters.getProperty("metodoGetEnum");
			}
			
			if (StringUtils.isNotEmpty(parameters.getProperty("metodoGetValue"))) {
				metodoGetValue = parameters.getProperty("metodoGetValue");
			}
			
			String className = parameters.getProperty("enumClassName");
			Class<?> returnType = null;

			try {
				enumClass = Class.forName(Constantes.ENUM_PACKAGE.concat(className));
				recreateStringMthd = enumClass.getMethod(metodoGetValue, new Class[] {});
				returnType = recreateStringMthd.getReturnType();
				recreateEnumMthd = enumClass.getMethod(metodoGetEnum, new Class[] { returnType });
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}			
	}

	@Override
	public int getSqlType() {
		return Types.INTEGER;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<APIEnum> returnedClass() {
		return (Class<APIEnum>) enumClass;
	}

	@Override
	public APIEnum nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner)
			throws SQLException {
		Integer value = rs.getInt(position);
		APIEnum returnVal = null;
		try {
			returnVal = (APIEnum) recreateEnumMthd.invoke(enumClass, new Object[] { value });
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return returnVal;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public boolean equals(APIEnum x, APIEnum y) {
		return ObjectUtils.nullSafeEquals(x, y);
	}

	@Override
	public int hashCode(APIEnum x) {
		return x.hashCode();
	}

	@Override
	public void nullSafeSet(PreparedStatement st, APIEnum value, int index,
			SharedSessionContractImplementor session) throws SQLException {
		Integer prepStmtVal = null;
		
		if (value == null) {
			st.setObject(index, null);
		} else {
			try {
				prepStmtVal = (Integer) recreateStringMthd.invoke(value, new Object[] {});
				st.setInt(index, prepStmtVal);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}		
	}

	@Override
	public APIEnum deepCopy(APIEnum value) {
		if (value == null) {
			return null;
		} else {
			return (APIEnum) value;
		}
	}

	@Override
	public Serializable disassemble(APIEnum value) {
		Object deepCopy = deepCopy(value);

		if (deepCopy instanceof Serializable) {
			return (Serializable) deepCopy;
		}

		return null;
	}

	@Override
	public APIEnum assemble(Serializable cached, Object owner) {
		return (APIEnum) deepCopy((APIEnum)cached);
	}
}
