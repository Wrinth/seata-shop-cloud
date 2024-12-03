package cn.mf5.shop.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_order")
public class Order {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private Integer num;
    private Long price;
    private Long userId;
    @TableField(exist = false) // 数据库不存在该字段（临时字段）
    private User user;
}