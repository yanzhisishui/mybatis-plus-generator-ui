import com.github.davidfantasy.mybatisplus.generatorui.GeneratorConfig;
import com.github.davidfantasy.mybatisplus.generatorui.MybatisPlusToolsApplication;
import com.github.davidfantasy.mybatisplus.generatorui.mbp.NameConverter;

public class TestApplication {


    public static void main(String[] args) {
        GeneratorConfig config = GeneratorConfig.builder()
                .jdbcUrl("jdbc:mysql://localhost:3306/yaorao-mall-product?characterEncoding=utf8&useAffectedRows=true&useSSL=false&serverTimezone=Asia/Shanghai&allowMultiQueries=true&allowPublicKeyRetrieval=true")
                .userName("root")
                .password("123456")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .nameConverter(new NameConverter() {
                    /**
                     * 自定义Service类文件的名称规则
                     */
                    public String serviceNameConvert(String tableName) {
                        return this.entityNameConvert(camel(tableName)) + "Service";
                    }

                    @Override
                    public String entityNameConvert(String tableName) {
                        return camel(tableName);
                    }

                    /**
                     * 自定义Controller类文件的名称规则
                     */
                    public String controllerNameConvert(String tableName) {
                        return this.entityNameConvert(camel(tableName)) + "Controller";
                    }
                }).basePackage("com.yaorao.mall.product.module.product").port(8068).build();

        MybatisPlusToolsApplication.run(config);

    }

    /**
     * 转驼峰 example sku_info ---> SkuInfo
     * */
    public static String camel(String origin){
        String[] split = origin.split("_");
        StringBuilder sb = new StringBuilder();

        for(String str : split){
            String first = str.substring(0, 1).toUpperCase();
            String end = str.substring(1);
            sb.append(first).append(end);
        }
        return sb.toString();
    }

}
