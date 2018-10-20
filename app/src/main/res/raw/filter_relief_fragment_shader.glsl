#extension GL_OES_EGL_image_external : require
//create file by ja333son 2018/2/28

precision mediump float;

varying vec2 vTexCoord;

uniform samplerExternalOES sTexture;

void main() {
    vec3 color = vec3(texture2D(sTexture, vTexCoord));
    gl_FragColor = vec4(1.0 - color, 0.0);
}