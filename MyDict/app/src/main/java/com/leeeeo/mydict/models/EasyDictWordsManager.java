package com.leeeeo.mydict.models;

import com.leeeeo.mydict.apps.AppEngine;

import de.greenrobot.dao.AbstractDao;

/**
 * Created by Jacob on 16/5/17.
 * Email: Jacob.Deng@about-bob.com
 */
public class EasyDictWordsManager extends BaseModelManager<EasyDictWords, Integer> {

    private static EasyDictWordsManager instance = null;
    private static DaoSession daoSession = null;

    public EasyDictWordsManager(AbstractDao pDao) {
        super(pDao);
    }


    public static EasyDictWordsManager getInstance() {
        if (instance == null) {
            daoSession = (DaoSession) AppEngine.getInstance().getDaoSession();
            instance = new EasyDictWordsManager(daoSession.getEasyDictWordsDao());
        }
        return instance;
    }


}
