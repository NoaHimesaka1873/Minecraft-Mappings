package org.mtr.mapping.mapper;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.MutableText;
import org.mtr.mapping.holder.Text;
import org.mtr.mapping.holder.TexturedButtonWidgetAbstractMapping;

public class TexturedButtonWidgetExtension extends TexturedButtonWidgetAbstractMapping {

	private final Identifier normalTexture;
	private final Identifier highlightedTexture;

	@MappedMethod
	public TexturedButtonWidgetExtension(int x, int y, int width, int height, Identifier normalTexture, Identifier highlightedTexture, org.mtr.mapping.holder.PressAction onPress) {
		this(x, y, width, height, normalTexture, highlightedTexture, onPress, "");
	}

	@MappedMethod
	public TexturedButtonWidgetExtension(int x, int y, int width, int height, Identifier normalTexture, Identifier highlightedTexture, org.mtr.mapping.holder.PressAction onPress, String message) {
		this(x, y, width, height, normalTexture, highlightedTexture, onPress, TextHelper.literal(message));
	}

	@MappedMethod
	public TexturedButtonWidgetExtension(int x, int y, int width, int height, Identifier normalTexture, Identifier highlightedTexture, org.mtr.mapping.holder.PressAction onPress, MutableText message) {
		super(x, y, width, height, 0, 0, 0, normalTexture, 256, 256, onPress, new Text(message.data));
		this.normalTexture = normalTexture;
		this.highlightedTexture = highlightedTexture;
	}

	@MappedMethod
	public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float delta) {
		final GuiDrawing guiDrawing = new GuiDrawing(graphicsHolder);
		guiDrawing.beginDrawingTexture(isHovered2() ? highlightedTexture : normalTexture);
		guiDrawing.drawTexture(getX2(), getY2(), getX2() + width, getY2() + height, 0, 0, 1, 1);
		guiDrawing.finishDrawingTexture();
	}

	@Deprecated
	@Override
	public final void renderWidget2(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
		GraphicsHolder.createInstanceSafe(guiGraphics, graphicsHolder -> render(graphicsHolder, mouseX, mouseY, delta));
	}

	@Deprecated
	@Override
	public final boolean mouseScrolled2(double mouseX, double mouseY, double verticalAmount) {
		return mouseScrolled3(mouseX, mouseY, verticalAmount);
	}

	@MappedMethod
	public boolean mouseScrolled3(double mouseX, double mouseY, double amount) {
		return super.mouseScrolled2(mouseX, mouseY, amount);
	}

	@MappedMethod
	@Override
	public final int getX2() {
		return super.getX2();
	}

	@MappedMethod
	@Override
	public final int getY2() {
		return super.getY2();
	}

	@MappedMethod
	@Override
	public final void setX2(int x) {
		super.setX2(x);
	}

	@MappedMethod
	@Override
	public final void setY2(int y) {
		super.setY2(y);
	}

	@MappedMethod
	@Override
	public final boolean isHovered2() {
		return super.isHovered2();
	}

	private static ResourceLocation formatIdentifier(Identifier identifier) {
		final String beginning = "textures/gui/sprites/";
		final String namespace = identifier.getNamespace();
		final String path = identifier.getPath();
		return new ResourceLocation(namespace, (path.startsWith(beginning) ? path.substring(beginning.length()) : path).replace(".png", ""));
	}
}
