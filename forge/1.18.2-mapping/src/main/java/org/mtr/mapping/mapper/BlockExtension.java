package org.mtr.mapping.mapper;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.BlockAbstractMapping;
import org.mtr.mapping.holder.Property;

public abstract class BlockExtension extends BlockAbstractMapping {

	@MappedMethod
	public BlockExtension(Properties properties) {
		super(properties.blockSettings);
	}

	@MappedMethod
	protected Property<?>[] blockProperties() {
		return new Property[0];
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		final Property<?>[] oldProperties = blockProperties();
		if (oldProperties.length > 0) {
			final net.minecraft.world.level.block.state.properties.Property<?>[] newProperties = new net.minecraft.world.level.block.state.properties.Property[oldProperties.length];
			for (int i = 0; i < oldProperties.length; i++) {
				newProperties[i] = oldProperties[i].data;
			}
			builder.add(newProperties);
		}
	}

	public static final class Properties {

		final BlockBehaviour.Properties blockSettings;

		@MappedMethod
		public Properties() {
			blockSettings = BlockBehaviour.Properties.of(Material.METAL);
		}

		private Properties(boolean blockPiston) {
			blockSettings = BlockBehaviour.Properties.of(blockPiston ? Material.HEAVY_METAL : Material.METAL);
		}

		private Properties(BlockBehaviour.Properties blockSettings) {
			this.blockSettings = blockSettings;
		}

		@MappedMethod
		public Properties blockPiston(boolean blockPiston) {
			return new Properties(blockPiston);
		}

		@MappedMethod
		public Properties luminance(int luminance) {
			return new Properties(blockSettings.lightLevel(blockState -> luminance));
		}
	}
}