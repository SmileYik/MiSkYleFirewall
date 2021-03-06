package com.github.miskyle.miskylepluginfirewall.cmd;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cmd {
  /**
   * 瀛愬懡浠�.
   * 
   * @return 瀛愬懡浠ゆ暟鍒�
   */
  String[] subCmd() default {};

  /**
   * 璁板綍鎸囦护鐨勯暱搴﹀強鍙傛暟瑕佹眰.
   * @return
   */
  String[] args() default {};

  /**
   * 璁板綍鎸囦护闇�瑕佺殑鏉冮檺, 榛樿涓烘棤鏉冮檺.
   * @return
   */
  String permission() default "";

  /**
   * 鎸囦护鐨勬敞閲�.
   * @return
   */
  String des();

  /**
   * 鏄惁闇�瑕佺帺瀹舵墽琛屾鎸囦护,榛樿涓簍rue.
   * @return
   */
  boolean needPlayer() default true;

  /**
   * 鎸囦护闀垮害鏄惁鏃犻檺闀�,榛樿涓篺alse.
   * @return
   */
  boolean unlimitedLength() default false;
}
