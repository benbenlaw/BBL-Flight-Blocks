package com.benbenlaw.flightblocks.event.client;

import com.benbenlaw.flightblocks.FlightBlocks;
import com.benbenlaw.flightblocks.block.entity.FlightBlockEntity;
import com.benbenlaw.flightblocks.block.entity.renderer.ClientRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.ShapeRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

@EventBusSubscriber(modid = FlightBlocks.MOD_ID)
public class LineRendererEvent {

    @SubscribeEvent
    public static void onRenderLevel(RenderLevelStageEvent.AfterTranslucentBlocks event) {

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || ClientRenderState.get() == null) return;

        BlockPos pos = ClientRenderState.get();
        BlockEntity be = mc.level.getBlockEntity(pos);
        if (!(be instanceof FlightBlockEntity flightBlock)) return;

        AABB box = flightBlock.createArea();

        PoseStack poseStack = event.getPoseStack();
        MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();

        Vec3 cam = mc.gameRenderer.getMainCamera().position();

        VertexConsumer lineBuilder = buffer.getBuffer(RenderTypes.lines());

        AABB shifted = box.move(-cam.x, -cam.y, -cam.z);

        ShapeRenderer.renderShape(poseStack, lineBuilder, Shapes.create(shifted), 0, 0, 0, ARGB.colorFromFloat(0.4F, 1, 1, 0),2f);

        buffer.endBatch();
    }
}
