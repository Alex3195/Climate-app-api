package uz.alex.climateappapi.constants;

public enum FileStoreEnum {
    FILES("/files"),

    TopicFiles("/topicFile"),

    ImageFile("/imageFile");

    FileStoreEnum(String folder) {
        this.folder = folder;
    }

    private String folder;

    public String getFolder() {
        return folder;
    }
}
