package br.com.goldencode.softsports;

public class Upload {

    private String mImageUrl;

    public Upload(){}

    public Upload(String imageUrl){
        mImageUrl = imageUrl;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
