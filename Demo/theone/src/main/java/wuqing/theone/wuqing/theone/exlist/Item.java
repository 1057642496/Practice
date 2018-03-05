package wuqing.theone.wuqing.theone.exlist;

/**
 * Created by 无情 on 2018/1/25.
 */

public class Item {
    private String name;
    private int imageId;
    public Item( int imageId,String name){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
