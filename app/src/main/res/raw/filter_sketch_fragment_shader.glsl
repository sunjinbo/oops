#extension GL_OES_EGL_image_external : require
//create file by ja333son 2018/2/28

precision mediump float;

varying vec2 vTexCoord;

uniform samplerExternalOES sTexture;

void main() {
    vec4 curColor = texture2D(sTexture,vTexCoord);
    float h = 0.299*curColor.x + 0.587*curColor.y + 0.114*curColor.z;
    vec4 fanshe = vec4(h,h,h,0.0);
    vec4 sample0,sample1,sample2,sample3;
    float h0,h1,h2,h3;
    float fstep=0.0015;
    sample0=texture2D(sTexture,vec2(vTexCoord.x-fstep,vTexCoord.y-fstep));
    sample1=texture2D(sTexture,vec2(vTexCoord.x+fstep,vTexCoord.y-fstep));
    sample2=texture2D(sTexture,vec2(vTexCoord.x+fstep,vTexCoord.y+fstep));
    sample3=texture2D(sTexture,vec2(vTexCoord.x-fstep,vTexCoord.y+fstep));
    h0 = 0.299*sample0.x + 0.587*sample0.y + 0.114*sample0.z;
    h1 = 0.299*sample1.x + 0.587*sample1.y + 0.114*sample1.z;
    h2 = 0.299*sample2.x + 0.587*sample2.y + 0.114*sample2.z;
    h3 = 0.299*sample3.x + 0.587*sample3.y + 0.114*sample3.z;
    sample0 = vec4(1.0-h0,1.0-h0,1.0-h0,0.0);
    sample1 = vec4(1.0-h1,1.0-h1,1.0-h1,0.0);
    sample2 = vec4(1.0-h2,1.0-h2,1.0-h2,0.0);
    sample3 = vec4(1.0-h3,1.0-h3,1.0-h3,0.0);
    vec4 color=(sample0+sample1+sample2+sample3) / 4.0;
    vec3 endColor = fanshe.rgb+(fanshe.rgb*color.rgb)/(1.0-color.rgb);
    gl_FragColor = vec4(endColor,0.0);
}