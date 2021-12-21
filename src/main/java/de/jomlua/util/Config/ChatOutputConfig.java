package de.jomlua.util.Config;

import de.jomlua.util.ChatOutput;

import java.io.File;

public class ChatOutputConfig extends Config{

    public ChatOutputConfig(File file) {
        super(file);

        for (ChatOutput message : ChatOutput.values()){
            message.setText(
                    translateColor((String) this.setDefault(message.getConfigName(), message.getText()))
            );
        }
    }

    public String getMessage(ChatOutput message){
        String path = message.getConfigName();
        if (this.contains(path)){
            return (String) this.get(path);
        }
        return null;
    }

    public void setMessage(ChatOutput message, String text){
        message.setText(nexline(translateColor(text)));
        //message.setText(nexline(text));
        this.set(message.getConfigName(),text);
    }
    private String translateColor(String text){
        return text.replace("&","§");
    }
    private String nexline(String tex){
        String line = System.lineSeparator();
        return tex.replace("%n",line);
    }
}
