package li.angu.gamehub.api.mail.html;

import java.util.*;

/*************************************************************************
 *
 * ANGULI NETWORKS CONFIDENTIAL
 * __________________
 *
 *  [2014] - [2018] Anguli Networks 
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Anguli Networks. The intellectual and 
 * technical concepts contained herein are proprietary to 
 * Anguli Networks and may be covered by German/EU and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Anguli Networks
 *
 * This File belongs to the GameHubAPI from Anguli Networks
 */
public class HTMLObject {

    private String name;
    private List<Object> children = new ArrayList<>();
    private Map<String, Object> attributes = new HashMap<>();

    public HTMLObject(String name) {
        this.name = name;
    }

    public HTMLObject(String name, Object children) {
        this(name, Collections.singletonList(children));
    }

    public HTMLObject(String name, List<Object> children, Map<String, Object> attributes) {
        this.name = name;
        this.children = children;
        this.attributes = attributes;
    }

    public HTMLObject(String name, Map<String, Object> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public HTMLObject(String name, List<Object> children) {

        this.name = name;
        this.children = children;
    }

    public HTMLObject addChildren(Object children) {
        this.children.add(children);
        return this;
    }

    public HTMLObject addChildren(List<Object> children) {
        this.children.addAll(children);
        return this;
    }

    public HTMLObject setAttribute(String key, Object value) {
        this.attributes.put(key, value);
        return this;
    }

    public Object getAttribute(String key) {
        return this.attributes.get(key);
    }

    private String getName() {
        return name;
    }

    private List<Object> getChildren() {
        return children;
    }

    private Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("<");

        stringBuilder.append(this.getName());

        this.getAttributes().forEach((key, value) -> {
            stringBuilder.append(" ");
            stringBuilder.append(key);
            stringBuilder.append("=\"");
            stringBuilder.append(value.toString());
            stringBuilder.append("\"");
        });

        if(this.getChildren().size() > 0) {
            stringBuilder.append(">");

            this.getChildren().forEach(object -> stringBuilder.append(object.toString()));

            stringBuilder.append("</");
            stringBuilder.append(this.getName());
        } else {
            stringBuilder.append("/");
        }

        stringBuilder.append(">");


        return stringBuilder.toString();
    }
}
