package comp1110.lab8;

import java.util.ArrayList;

public class FamilyTree {
    /**
     * This function accepts an Individual <code>ancestor</code> representing
     * the root (common ancestor) of a family tree
     * and the name of a target individual to find within that family tree,
     * and returns a string representation of all the ancestors of that
     * individual, each separated by the string " born of ".
     * <p>
     * If target name matches the name of <code>ancestor</code>, then only
     * the target name is returned.
     * <p>
     * If the target name is not found within the family tree descended from
     * <code>ancestor</code>, then null is returned.
     * <p>
     * For example, given an Individual homer representing a person named
     * "Homer", who has children named "Lisa" and "Bart":
     * getAncestry(homer, "Lisa") returns "Lisa born of Homer";
     * getAncestry(homer, "Bart") returns "Bart born of Homer"; and
     * getAncestry(homer, "Homer") returns "Homer"; and
     * getAncestry(homer, "Barney") returns null
     * <p>
     * Note: each individual has only one parent in the family tree.
     *
     * @param ancestor   the root (common ancestor) of a family tree
     * @param targetName the name of an individual to find in the family tree
     * @return a String representing the ancestry of the individual named
     * <code>targetName</code>, or null if no such individual is found
     */
    public static String getAncestry(Individual ancestor, String targetName) {
        if (ancestor.name.equals(targetName)) return targetName;
        if (ancestor.children==null) return null;
        ArrayList<Individual> ancestorList = getChildAncestors(ancestor,targetName,new ArrayList<>());

        if (!ancestorList.isEmpty()){
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = ancestorList.size()-1;i>0;i--){
                stringBuilder.append(ancestorList.get(i).name).append(" born of ");
            }
            stringBuilder.append(ancestorList.get(0).name);
            return stringBuilder.toString();
        }

        // FIXME complete this method
        return null;
    }


    public static ArrayList<Individual> getChildAncestors(Individual ancestor, String targetName, ArrayList<Individual> ancestorsList){
        if (ancestor.children != null){
            ancestorsList.add(ancestor);
            for (Individual child : ancestor.children){
                //recursion
                if (child.name.equals(targetName)){
                    ancestorsList.add(child);
                    return ancestorsList;
                }else if (child.children != null){
                    getChildAncestors(child,targetName,ancestorsList);
                }
            }
            //After recursion
            if (ancestorsList.get(ancestorsList.size()-1).equals(ancestor)){
                ancestorsList.remove(ancestor);
            }
        }
        return ancestorsList;
    }

    /**
     * This class represents an individual with zero or more children.
     */
    static class Individual {
        public String name;
        /**
         * This individual's immediate descendants.
         * If this individual has no children, this field is null.
         */
        public Individual[] children;

        public Individual(String name, Individual[] children) {
            this.name = name;
            this.children = children;
        }
    }
}
