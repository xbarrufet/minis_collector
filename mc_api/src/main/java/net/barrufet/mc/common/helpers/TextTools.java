package net.barrufet.mc.common.helpers;

public class TextTools {

    public static String buildId(String entityName) {
        return entityName.replace(' ', '_').toLowerCase();
    }

    public static String buildIdFromParent(String entityName, String parentID) {
        return buildId(entityName)  + "_" + parentID;
    }

    public static boolean isValidId(String id,String name, String parentID) {
        if(parentID == null || parentID.isEmpty()){
            return id.trim().equals(buildId(name));
        } else {
            return id.trim().equals(buildIdFromParent(name, parentID));
        }
    }
}
