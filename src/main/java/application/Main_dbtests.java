package application;

import data_util.HomeFolderService;
import data_util.OperationSystemData;

public class Main_dbtests {
    public static void main(String[] args)
    {
        System.out.println(OperationSystemData.os);
        System.out.println(OperationSystemData.osUser);
        System.out.println(OperationSystemData.osHome);

        HomeFolderService homeFolder = new HomeFolderService();

    }
}
