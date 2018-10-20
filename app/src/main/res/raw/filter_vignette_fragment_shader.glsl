#extension GL_OES_EGL_image_external : require
//create file by ja333son 2018/2/28

precision highp float;

varying vec2 vTexCoord;

uniform samplerExternalOES sTexture;
uniform float fit_time_x;
uniform float fit_time_y;

float randValue(float src) {
    float flag;
    if(src > 0.5) {
        flag = 2.0 * (src - 0.5);
    } else {
        flag = -2.0 * (src);
    }
    return flag;
}

void main() {
    vec3 color = texture2D(sTexture, vec2(vTexCoord.x + randValue(fit_time_x) * 0.01, vTexCoord.y + randValue(fit_time_y) * 0.01)).rgb;
    float gray = (color.r + color.g + color.b) / 3.0;
    vec3 grayscale = vec3(gray);
    gl_FragColor = mix(texture2D(sTexture, vTexCoord), vec4(grayscale, 1.0), 0.3);
}