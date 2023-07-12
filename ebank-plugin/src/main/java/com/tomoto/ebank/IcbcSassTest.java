package com.tomoto.ebank;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.internal.util.internal.util.fastjson.JSON;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankEnterpriseTradeQhisdRequestV1;
import com.icbc.api.response.MybankEnterpriseTradeQhisdResponseV1;
import com.icbc.api.utils.IcbcSignature;
import com.tomoto.ebank.util.Base64Utils;

/**
 * @Author zhxy
 * @Date 2023/7/10 2:38 下午
 * @Version V1.0
 **/
public class IcbcSassTest {

    public static void main(String[] args) {
        genSubMechant();
    }

    protected static String APP_ID = "11000000000000008151";
    //protected static String APP_ID = "10000000000000241121";
    //protected static String password = "12345678";
    //protected static String ca = "MIIDDTCCAfWgAwIBAgIKG5LKECVWAANH+DANBgkqhkiG9w0BAQUFADA7MR8wHQYDVQQDExZJQ0JDIFRlc3QgQ29ycG9yYXRlIENBMRgwFgYDVQQKEw90ZXN0aWNiYy5jb20uY24wHhcNMjIwNzAxMDMxOTM5WhcNMjcwNzAxMDMxOTM5WjBBMRYwFAYDVQQDDA1kbGp0Z2YueS4yNjAzMQ0wCwYDVQQLDAQyNjAzMRgwFgYDVQQKDA90ZXN0aWNiYy5jb20uY24wgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBAKsD7ccygqhTG8v+OXTIGgIJ7IEd4erG8KGektX4lHanIv0vCl66QyQJuusU/gW+xAbVhUZHNEeups4GYKUBTSihEMK/lct5HcxDdC1hEcTCJriXekkOzgWSfurTzCawzzXYXrc3zGXmzMP+E4JqNjfTtuhxQB0R6ltj837QWREvAgMBAAGjgZAwgY0wHwYDVR0jBBgwFoAURH23kCw3pNntbOKkh1dnCrXwTRQwSwYDVR0fBEQwQjBAoD6gPKQ6MDgxDjAMBgNVBAMMBWNybDQ0MQwwCgYDVQQLDANjcmwxGDAWBgNVBAoMD3Rlc3RpY2JjLmNvbS5jbjAdBgNVHQ4EFgQUCKF/9xR38QsvmGpLbDBKZhwDqoswDQYJKoZIhvcNAQEFBQADggEBACnyD8pgNHUigW+KdHoKHVcM/AqghBMxtg36TUZEfysK3tPhpCGOwSdte4dSNP4ivtJnXWjjLKNetuaCT/38IZbOggBTcPECe1gZYjIbfNv2g42xPW2VFn7CS5vbKm4kI5+1zc256IgHQ2umOCMLC67fxlfl2kqYH2BC0WWe9i+0GW48X5JaVM3XXnrQN+mZZtbsFzz5e1BHtfYhmYysDqnJ1aSejLaPeOSznZbgAfWS7GyTJ4D0504MJFAyk15Cf1/4fiKavI3Gndu8UKCwKNI6Ys0CKvEgzRqEtcmzpHW+bkX1pxnODuNDdTQSXXOcuyV9teLuX9jNgA6B0HIsgH0=";
    protected static String PRI_KEY_PATH = "/Users/heronsbill/Documents/icbc_test/privateKey.pri";
    protected static String SM_CA_PATH = "/Users/heronsbill/Documents/icbc_test/ceshi202306.y.1001.cer";
    protected static String APIGW_PUBLIC_KEY_PATH = "/Users/heronsbill/Documents/icbc_test/API_GATEWAY_ICBC_SM.cer";

    protected static final String APIGW_PUBLIC_KEY = IcbcSignature.getCAInfoStr(APIGW_PUBLIC_KEY_PATH);
    protected static String SM_CA = IcbcSignature.getCAInfoStr(SM_CA_PATH);
//    protected static String PRI_KEY = Base64Utils.fileToBase64(PRI_KEY_PATH);
    protected static String PRI_KEY = "d8c5ff5d7d893dea8243ecf9ef0a6fb94d99ab806a68a5e5289c46d5a0aadf5f";


    public static void genSubMechant() {

        System.out.println(PRI_KEY);

        System.out.println("sm_ca:" + SM_CA);
        System.out.println("APIGW_PUBLIC_KEY:" + APIGW_PUBLIC_KEY);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_CA_SM_ICBC, PRI_KEY,
                IcbcConstants.CHARSET_UTF8, IcbcConstants.FORMAT_JSON, null, null, null, SM_CA, null);
        client.setIcbc_ca(APIGW_PUBLIC_KEY);
        MybankEnterpriseTradeQhisdRequestV1 request = new MybankEnterpriseTradeQhisdRequestV1();
        request.setServiceUrl("https://apipcs3.dccnet.com.cn/api/mybank/enterprise/trade/qhisd/V1");

        String insid = "SEQ" + System.currentTimeMillis();
        MybankEnterpriseTradeQhisdRequestV1.MybankEnterpriseTradeQhisdRequestBizV1 bizContent = new MybankEnterpriseTradeQhisdRequestV1.MybankEnterpriseTradeQhisdRequestBizV1();
        bizContent.setTransCode("QHISD");
        bizContent.setTranDate("20220701");
        bizContent.setTranTime("080811111");
//        bizContent.setAccountNo("2603037819200044012");
        bizContent.setAccountNo("1001164819002100255");
        bizContent.setLanguage("zh_CN");
        bizContent.setfSeqno("QHISD02216797552");
        bizContent.setBeginDate("20220701");
        bizContent.setEndDate("20220825");
        //bizContent.setQryfSeqno("PAYENT02216797551");


        request.setBizContent(bizContent);
        System.out.println("REQ:" + JSONObject.toJSON(request));

        System.err.println(request.getBizContent().toString());
        //System.out.print("REQ:" + JSON.toJSON(request));
        MybankEnterpriseTradeQhisdResponseV1 response;
        try {
            response = (MybankEnterpriseTradeQhisdResponseV1) client.execute(request, System.currentTimeMillis() + "");
            System.out.print("RESPONSE:" + JSON.toJSON(response));
            if (response.isSuccess()) {
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("response:" + response);
                //System.out.println("ca:" + ca);
                System.out.println("PRI_KEY:" + PRI_KEY);
                System.out.println("APIGW_PUBLIC_KEY:" + APIGW_PUBLIC_KEY);
                //System.out.println("password:" + password);

            } else {
                System.out.println("ReturnCode:" + response.getReturnCode());
                System.out.println("ReturnMsg:" + response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
