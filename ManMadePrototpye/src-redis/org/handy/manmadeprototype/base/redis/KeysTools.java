package org.handy.manmadeprototype.base.redis;

/**
 * Created by zhongming on 15/12/18.
 */
public class KeysTools {

    public static class ACCOUNTKEYS {

        /**
         * 根据account得到对应Key值
         * @param account
         * @return
         */
        public static String accountKey(String account) {

            return "ACCOUNT_NAME_" + account;
        }

        /**
         * 根据account ID得到对应Key值
         * @param id
         * @return
         */
        public static String accountIdKey(Integer id) {

            return "ACCOUNT_ID_" + id;
        }

    }
}
