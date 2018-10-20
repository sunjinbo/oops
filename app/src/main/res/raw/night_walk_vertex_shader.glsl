attribute vec4 aPosition;
attribute vec4 aTexCoord;

uniform highp float texelWidth;
uniform highp float texelHeight;

varying vec2 textureCoordinate;

varying vec2 leftTextureCoordinate;
varying vec2 rightTextureCoordinate;

varying vec2 topTextureCoordinate;
varying vec2 topLeftTextureCoordinate;
varying vec2 topRightTextureCoordinate;

varying vec2 bottomTextureCoordinate;
varying vec2 bottomLeftTextureCoordinate;
varying vec2 bottomRightTextureCoordinate;

void main() {
	gl_Position = aPosition;

    vec2 widthStep = vec2(texelWidth, 0.0);
    vec2 heightStep = vec2(0.0, texelHeight);
    vec2 widthHeightStep = vec2(texelWidth, texelHeight);
    vec2 widthNegativeHeightStep = vec2(texelWidth, -texelHeight);

    textureCoordinate = vec2(aTexCoord.x, 1.0 - aTexCoord.y);
    leftTextureCoordinate = textureCoordinate.xy - widthStep;
    rightTextureCoordinate = textureCoordinate.xy + widthStep;

    topTextureCoordinate = textureCoordinate.xy - heightStep;
    topLeftTextureCoordinate = textureCoordinate.xy - widthHeightStep;
    topRightTextureCoordinate = textureCoordinate.xy + widthNegativeHeightStep;

    bottomTextureCoordinate = textureCoordinate.xy + heightStep;
    bottomLeftTextureCoordinate = textureCoordinate.xy - widthNegativeHeightStep;
    bottomRightTextureCoordinate = textureCoordinate.xy + widthHeightStep;
}