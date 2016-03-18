import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * Created by Thea on 10.03.2016.
 */
public class Brick extends Rectangle{
    int tmp = 0;
    int x;
    int y;
    int w;
    int h;
    public Brick(int x, int y, int w, int h, Color color) {
        super(x,y,w,h);
        this.setFill(color);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public boolean collide(double bx, double by, double r){
        double x0 = x - r;
        double y0 = y - r;
        double x1 = x + w + r;
        double y1 = y + h + r;
        if(bx >= x0 && by >= y0 && bx <= x1 && by <= y1) {
            double[] list = new double[4];

                double tmp1 = (bx - x0);
                tmp1 = Math.pow(tmp1, 2);
                double tmp2 = (by - y0);
                tmp2 = Math.pow(tmp2, 2);
                double tmp3 = tmp1 + tmp2;
                list[0] = Math.sqrt(tmp3);

                tmp1 = (bx - x0);
                tmp1 = Math.pow(tmp1, 2);
                tmp2 = (y1 - by);
                tmp2 = Math.pow(tmp2, 2);
                tmp3 = tmp1 + tmp2;
                list[1] = Math.sqrt(tmp3);

                tmp1 = (x1 - bx);
                tmp1 = Math.pow(tmp1, 2);
                tmp2 = (by - y0);
                tmp2 = Math.pow(tmp2, 2);
                tmp3 = tmp1 + tmp2;
                list[2] = Math.sqrt(tmp3);

                tmp1 = (x1 - bx);
                tmp1 = Math.pow(tmp1, 2);
                tmp2 = (y1 - by);
                tmp2 = Math.pow(tmp2, 2);
                tmp3 = tmp1 + tmp2;
                list[3] = Math.sqrt(tmp3);

            double[] list1 = new double[4];
            list1[0] = list[0] + list[1];
            list1[1] = list[3] + list[2];
            list1[2] = list[0] + list[2];
            list1[3] = list[3] + list[1];

            double tmp0 = list1[0];
            int tmp5 = 0;
            for(int i = 1; i < 4; i++){
                if(list1[i] < tmp0){
                    tmp5 = i;
                    tmp0 = list1[i];
                }
            }
            if(tmp5 < 2){
                tmp = 1;
            }else{
                tmp = 2;
            }

            return true;
        }else return false;
    }
}
