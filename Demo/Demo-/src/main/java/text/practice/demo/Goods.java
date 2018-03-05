package text.practice.demo;

import android.support.annotation.NonNull;

import java.util.Collection;

/**
 * Created by 无情 on 2018/1/19.
 */

public  class Goods implements Comparable<Goods> {
    private String id;
    private String goodsName;
    private String codeBar;
    private float num;
    private float curPrice;
    private float money;

    public Goods() {
        super();
    }

    public Goods(String id, String goodsName, String codeBar, float num,
                 float curPrice, float money) {
        super();
        this.id = id;
        this.goodsName = goodsName;
        this.codeBar = codeBar;
        this.num = num;
        this.curPrice = curPrice;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getCodeBar() {
        return codeBar;
    }

    public void setCodeBar(String codeBar) {
        this.codeBar = codeBar;
    }

    public float getNum() {
        return num;
    }

    public void setNum(float num) {
        this.num = num;
    }

    public float getCurPrice() {
        return curPrice;
    }

    public void setCurPrice(float curPrice) {
        this.curPrice = curPrice;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }




    /**
     * compareTo（）方法，该方法的返回值0代表相等，1表示大于，-1表示小于；
     */
    @Override
    public int compareTo( Goods o) {
        int i = (int) (this.getCurPrice() - o.getCurPrice());   //先按照现价排序
        if (i == 0){
            return (int) (this.getMoney() - o.getMoney());//如果现价相等了再用钱来进行排序
        }
        return i;
    }
}
