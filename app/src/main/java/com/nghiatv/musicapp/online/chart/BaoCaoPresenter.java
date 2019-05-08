package com.nghiatv.musicapp.online.chart;

public class BaoCaoPresenter implements IBaoCaoContract.IPresenter {
    private BaoCaoModel baoCaoModel;
    private IBaoCaoContract.IView iView;

    public BaoCaoPresenter(IBaoCaoContract.IView iView) {
        this.iView = iView;
        baoCaoModel = new BaoCaoModel();
    }

    @Override
    public void getSongs() {
        iView.showSongs(
                baoCaoModel.getSongs1(),
                baoCaoModel.getSongs2(),
                baoCaoModel.getSongs3(),
                baoCaoModel.getSongs4(),
                baoCaoModel.getSongs5()
        );
    }
}
