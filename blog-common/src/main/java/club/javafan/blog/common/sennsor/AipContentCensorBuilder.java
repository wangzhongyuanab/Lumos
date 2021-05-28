package club.javafan.blog.common.sennsor;

import com.baidu.aip.contentcensor.AipContentCensor;
import org.apache.tomcat.util.http.fileupload.MultipartStream;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

/**
 * 文本审核单例
 */
@Component
public class AipContentCensorBuilder {
    private static final Logger logger = LoggerFactory.getLogger(AipContentCensorBuilder.class);


    private static AipContentCensor client = new AipContentCensor("24124889", "Wylx0CZSZ0BeYLH0Z0M5uO1n"
            , "xfSy6XPeG0iP5Tt72vO8dREbpBGFPGzC");

    public static AipContentCensor getInstance() {
        return client;
    }

    private AipContentCensorBuilder() {

    }

    /**
     *  敏感词结果
     */
    public static class SensorResult{
        private String desc;
        private Integer code;

        public String getDesc() {
            return desc;
        }

        public SensorResult setDesc(String desc) {
            this.desc = desc;
            return this;
        }

        public Integer getCode() {
            return code;
        }

        public SensorResult setCode(Integer code) {
            this.code = code;
            return this;
        }

        public SensorResult failResult(String desc){
            SensorResult sensorResult = new SensorResult();
            sensorResult.setCode(INTEGER_ONE);
            sensorResult.setDesc(desc);
            return sensorResult;
        }

        public SensorResult successResult(String desc) {
            SensorResult sensorResult = new SensorResult();
            sensorResult.setCode(INTEGER_ZERO);
            sensorResult.setDesc(desc);
            return sensorResult;
        }
    }

    /**
     * 文本敏感词校验
     * @param text
     * @return
     */
    public static SensorResult judgeText(String text){
        JSONObject jsonObject = client.textCensorUserDefined(text);
        //JSONObject jsonObject = client.antiSpam(text, null);
        logger.info("AipContentCensorBuilder text: {},info: {}",text,jsonObject);
        //0表示非违禁，1表示违禁，2表示建议人工复审
        //1表示合规 2表示不合规
        Integer conclusionType = jsonObject.getInt("conclusionType");
        if (Objects.isNull(jsonObject) || Objects.isNull(conclusionType) || conclusionType == INTEGER_ONE){
            return new SensorResult().successResult("需要人工审核");
        }
        return new SensorResult().failResult("失败！");
    }
}
