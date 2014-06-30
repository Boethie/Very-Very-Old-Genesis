package genesis.lib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A custom annotation for our own mod. <br />
 * Use it for defining the author of a class in this mod. <br />
 * Sure you could use a comment, but this makes it more readable and official.<br /><br />
 * It can be applied to any element in a class - the class itself, any methods/fields, etc.
 * @author Arbiter
 *
 */
@Author("Arbiter")
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Author
{
	/**
	 * The default value holding the author of the type. <br />
	 * You do not need a variable declaration in the annotation as it is the default value.<br /><br />
	 * You do not need to do this: <code>@Author(value="author")</code>, you can just do this: <code>@Author("author")</code>
	 * @return
	 */
	String value();
	
	/**
	 * Use this as an optional field if there were additional people who have helped you. <br />
	 * If you use this, make sure to label the sole author as well.
	 * @return
	 */
	String[] help() default {""};
}