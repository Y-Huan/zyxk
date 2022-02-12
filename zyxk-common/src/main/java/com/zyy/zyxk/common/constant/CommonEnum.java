package com.zyy.zyxk.common.constant;

public class CommonEnum {
    public enum AuthorityType{
        Api("接口", 1),
        Menu("菜单", 2),
        Button("按钮", 3),
        Undefined("未定义",-1);

        // 成员变量
        private String name;
        private Integer value;

        // 构造方法
        private AuthorityType(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        // 普通方法
        public static AuthorityType getEnum(Integer value) {
            for (AuthorityType item : AuthorityType.values()) {
                if (item.value == value) {
                    return item;
                }
            }

            return AuthorityType.Undefined;
        }

        // get set 方法
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Integer getValue() {
            return value;
        }
        public void setValue(Integer value) {
            this.value = value;
        }
    }

    public enum PictureResourceType{

        inventoryWarehouse("仓库", 1,"zyxk/images/warehouse/","zyxk/images/static/warehouse_default.png"),
        inventoryProduct("物品", 2,"zyxk/images/product/","zyxk/images/static/goods_default.png"),
        operorder("出入库单",3,"zyxk/images/operorder",""),
        Undefined("未定义",-1,"","");

        // 成员变量
        private String name;
        private Integer value;
        private String parentPath;
        private String defaultUrl;

        // 构造方法
        private PictureResourceType(String name, Integer value, String parentPath, String defaultUrl) {
            this.name = name;
            this.value = value;
            this.parentPath = parentPath;
            this.defaultUrl = defaultUrl;
        }

        // 普通方法
        public static PictureResourceType getEnum(Integer value) {
            for (PictureResourceType item : PictureResourceType.values()) {
                if (item.value == value) {
                    return item;
                }
            }
            return PictureResourceType.Undefined;
        }

        // get set 方法
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Integer getValue() {
            return value;
        }
        public void setValue(Integer value) {
            this.value = value;
        }
        public String getParentPath() {
            return parentPath;
        }
        public void setParentPath(String parentPath) {
            this.parentPath = parentPath;
        }

        public String getDefaultUrl() {
            return defaultUrl;
        }

        public void setDefaultUrl(String defaultUrl) {
            this.defaultUrl = defaultUrl;
        }
    }

    public enum ProductUnitType{

        UnitForItem("按件", 1),
        UnitForWeight("按重量", 2),
        Undefined("未定义",-1);

        // 成员变量
        private String name;
        private Integer value;

        // 构造方法
        private ProductUnitType(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        // 普通方法
        public static PictureResourceType getEnum(Integer value) {
            for (PictureResourceType item : PictureResourceType.values()) {
                if (item.value == value) {
                    return item;
                }
            }
            return PictureResourceType.Undefined;
        }

        // get set 方法
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Integer getValue() {
            return value;
        }
        public void setValue(Integer value) {
            this.value = value;
        }
    }

    public enum OperationOrderType{
        SaleIn("采购入库", 1),
        OtherIn("其他入库", 2),
        ConsumingOut("领用出库", 3),
        ReturnOut("退货出库", 4),
        LossOut("损耗出库", 5),
        OtherOut("其他出库", 6),
        AdjustIn("入库调整单", 7),
        AdjustOut("出库调整单", 8),
        InitIn("初始化入库", 9),
        ReviewPart("盘库单", 10),
        ReviewAll("核算单", 11),
        Undefined("未定义",-1);

        // 成员变量
        private String name;
        private Integer value;

        // 构造方法
        private OperationOrderType(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        // 普通方法
        public static OperationOrderType getEnum(Integer value) {
            for (OperationOrderType item : OperationOrderType.values()) {
                if (item.value == value) {
                    return item;
                }
            }
            return OperationOrderType.Undefined;
        }

        // get set 方法
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Integer getValue() {
            return value;
        }
        public void setValue(Integer value) {
            this.value = value;
        }
    }

    public enum OperationOrderDetailType{
        SaleIn("采购入库", 1),
        OtherIn("其他入库", 2),
        ConsumingOut("领用出库", 3),
        ReturnOut("退货出库", 4),
        LossOut("损耗出库", 5),
        OtherOut("其他出库", 6),
        AdjustIn("入库调整", 7),
        AdjustOut("出库调整", 8),
        InitIn("初始化入库", 9),
        ReviewPartIn("盘盈入库", 10),
        ReviewPartOut("盘亏出库", 11),
        ReviewAllIn("核算入库", 12),
        ReviewAllOut("核算出库", 13),
        Undefined("未定义",-1);

        // 成员变量
        private String name;
        private Integer value;

        // 构造方法
        private OperationOrderDetailType(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        // 普通方法
        public static OperationOrderDetailType getEnum(Integer value) {
            for (OperationOrderDetailType item : OperationOrderDetailType.values()) {
                if (item.value == value) {
                    return item;
                }
            }
            return OperationOrderDetailType.Undefined;
        }

        // get set 方法
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Integer getValue() {
            return value;
        }
        public void setValue(Integer value) {
            this.value = value;
        }
    }
}
