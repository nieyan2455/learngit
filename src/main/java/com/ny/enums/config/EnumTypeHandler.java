package com.ny.enums.config;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.ny.enums.BaseEnum;

/**
 *
 *
 */
public class EnumTypeHandler<E extends Enum<?> & BaseEnum> extends BaseTypeHandler<BaseEnum> {

    private Class<E> type;

    public EnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, BaseEnum baseEnum, JdbcType jdbcType)
            throws SQLException {
        preparedStatement.setInt(i, baseEnum.getCode());
    }

    @Override
    public BaseEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int i = resultSet.getInt(s);
        if (resultSet.wasNull()) {
            return null;
        } else {
            try {
                return EnumUtil.codeOf(type, i);
            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "Cannot convert " + i + " to " + type.getSimpleName() + " by ordinal value.");
            }
        }
    }

    @Override
    public BaseEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int n = resultSet.getInt(i);
        if (resultSet.wasNull()) {
            return null;
        } else {
            try {
                return EnumUtil.codeOf(type, n);
            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "Cannot convert " + n + " to " + type.getSimpleName() + " by ordinal value.");
            }
        }
    }

    @Override
    public BaseEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int n = callableStatement.getInt(i);
        if (callableStatement.wasNull()) {
            return null;
        } else {
            try {
                return EnumUtil.codeOf(type, n);
            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "Cannot convert " + n + " to " + type.getSimpleName() + " by ordinal value.");
            }
        }
    }
}
