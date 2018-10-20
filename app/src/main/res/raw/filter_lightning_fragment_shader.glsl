//create file by ja333son 2018/3/6

precision mediump float;

uniform sampler2D srcTexture;
uniform sampler2D cacheTexture;
uniform float fit_time;
varying vec2 vTexCoord;
void main() {
    if(fit_time > 0.5 && fit_time < 1.0) {
        vec4 prevColor = texture2D(cacheTexture, vTexCoord);
        vec3 transColor = vec3(1.0 - prevColor);
        gl_FragColor = vec4(transColor.rgb, 0.1);
    } else {
        gl_FragColor = texture2D(srcTexture, vTexCoord);
    }
}