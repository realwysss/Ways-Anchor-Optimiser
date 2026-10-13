package com.wayysss.anchor.mixin;

import com.wayysss.anchor.cfg;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class mcclmixin {

    @Shadow
    private int itemUseCooldown;

    @Shadow
    public int attackCooldown;

    @Shadow
    public ClientPlayerEntity player;

    @Shadow
    public ClientWorld world;
    @Shadow
    public net.minecraft.util.hit.HitResult crosshairTarget;

    @Inject(method = "doItemUse", at = @At("HEAD"))
    private void itu(org.spongepowered.asm.mixin.injection.callback.CallbackInfo ci) {
        if (!cfg.on) return;
        if (this.player != null && this.world != null) {
            boolean b = false;
            net.minecraft.item.Item m = this.player.getMainHandStack().getItem();
            if (m != null && (m.getTranslationKey().contains("respawn_anchor") || m.getTranslationKey().contains("glowstone"))) b = true;
            net.minecraft.item.Item o = this.player.getOffHandStack().getItem();
            if (o != null && (o.getTranslationKey().contains("respawn_anchor") || o.getTranslationKey().contains("glowstone"))) b = true;
            
            if (b && this.crosshairTarget != null && this.crosshairTarget.getType() == net.minecraft.util.hit.HitResult.Type.ENTITY) {
                net.minecraft.util.hit.HitResult blockHit = this.player.raycast(4.5D, 1.0F, false);
                if (blockHit != null && blockHit.getType() == net.minecraft.util.hit.HitResult.Type.BLOCK) {
                    this.crosshairTarget = blockHit;
                }
            }
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tck(CallbackInfo c) {
        if (!cfg.on) return;
        if (this.player != null && this.world != null) {
            boolean b = false;
            Item m = this.player.getMainHandStack().getItem();
            if (m != null && (m.getTranslationKey().contains("respawn_anchor") || m.getTranslationKey().contains("glowstone"))) {
                b = true;
            }
            Item o = this.player.getOffHandStack().getItem();
            if (o != null && (o.getTranslationKey().contains("respawn_anchor") || o.getTranslationKey().contains("glowstone"))) {
                b = true;
            }
            if (!b) return;

            if (this.itemUseCooldown > 0) {
                int t = com.wayysss.anchor.cfg.min + (com.wayysss.anchor.cfg.max > com.wayysss.anchor.cfg.min ? (int)(Math.random() * ((com.wayysss.anchor.cfg.max - com.wayysss.anchor.cfg.min) + 1)) : 0);
                if (this.itemUseCooldown > t) {
                    this.itemUseCooldown = t;
                }
            }
            if (this.attackCooldown > 0) {
                this.attackCooldown = 0;
            }
        }
    }
}



