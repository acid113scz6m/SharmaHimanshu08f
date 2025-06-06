package com.fhs.core.trans.anno;

import com.fhs.core.trans.vo.TransPojo;
import com.fhs.core.trans.vo.VO;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 翻译
 *
 * @author wanglei
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Trans {

    /**
     * 获取翻译类型，比如 wordbook 是字典
     *
     * @return 类型
     */
    String type();

    /**
     * 字段 比如  要翻译男女 上面的type写dictionary 此key写sex即可
     *
     * @return
     */
    String key() default "";

    /**
     * 设置到的target value  比如我有一个sex字段，有一个sexName 字段  sex是0 设置ref翻译服务可以自动把sexname设置为男
     * 目标类字段配置了多个 有teacherName,teacherage 两个字段   我想要teacherName  可以写 teacherName#name
     *
     * @return
     */
    String ref() default "";

    /**
     * ref 支持多个，为了保持兼容新加了一个字段
     * 作用同ref 只是支持多个
     *
     * @return
     */
    String[] refs() default {};

    /**
     * 目标class
     *
     * @return
     */
    Class<? extends VO> target() default TransPojo.class;

    /**
     * 需要目标class哪些字段
     *
     * @return
     */
    String[] fields() default {};

    /**
     * 别名
     *
     * @return
     */
    String alias() default "";

    /**
     * 远程服务名称
     *
     * @return
     */
    String serviceName() default "";

    /**
     * 远程服务 ContextPath
     *
     * @return
     */
    String serviceContextPath() default "";

    /**
     * @return
     */
    String targetClassName() default "";

    /**
     * 自定义的函数名(此名称需要被spring托管 并实现FuncGetter)
     * @return
     */
    String customeBeanFuncName() default "";

    /**
     * 数据源
     * @return
     */
    String dataSource() default "";

    /**
     *  唯一键字段
     * 部分的时候表里的code，身份证号码，手机号等也是唯一键
     * @return
     */
    String uniqueField() default "";

    /**
     *  排序字段
     * 部分的时候表里的code，身份证号码，手机号等也是唯一键
     * @return
     */
    int sort() default 0;
}
