package by.it.rumiantcau.jd02_01;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

class Buyer extends Thread implements IBuyer, IUseBacket{
    //в этой хашмапе будет хранится товары, купленые покупателем, где Кей - номер покупатиля, Валью - стринг с
    //купленными товарами
    static Map<Buyer, String> basket = new HashMap<>();
        public Buyer(int number) {
        super("Buyer №"+number+" ");
        Dispatcher.countBuyer++;
    }



    @Override
    public void run() {
        enterToMarket();
        takeBacket();
        chooseGoods();
        putGoodsToBacket();
        goOut();
    }

    @Override
    public void enterToMarket() {
        System.out.println(this+"вошел в магазин");
    }
    @Override
    public void takeBacket() {
        System.out.println(this+"взял корзинку для товаров");
    }

    @Override
    public void chooseGoods() {
        System.out.println(this+"начал выбирать товары");
        int timeout= Helper.random(500,2000);
        Helper.sleep(timeout);
    }
    @Override
    public void putGoodsToBacket() {
        final AtomicInteger countGoods = new AtomicInteger(Helper.random(1, 4));
        StringBuilder str = new StringBuilder("");
        while (countGoods.doubleValue() != 0) {
            System.out.print(this);
            //складываем товары покупателя в стрингбилдер
            str.append(Good.putGoodToBacket()).append(" | ");
            countGoods.getAndDecrement();
            if (countGoods.doubleValue() == 0)
                basket.put(this, str.toString());
        }
        System.out.println(this+"закончил выбирать товары");
        System.out.println("товары в корзине покупателя " + this + ": " + basket.get(this));
        System.out.println("" + this+"закончил ложить товары в корзину");
    }



    @Override
    public void goOut() {
        System.out.println(this+"вышел из магазина");
        Dispatcher.countBuyer--;
    }


    @Override
    public String toString() {
        return this.getName();
    }




}
