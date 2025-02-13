package elocindev.shield_overhaul.event;

import elocindev.shield_overhaul.registry.PacketRegistry;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.network.ClientPlayerEntity;

public class ShieldInteraction {
    public static void bashingPacketRegistry() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null || client.options == null) return;

            ClientPlayerEntity player = client.player;

            if (client.options.attackKey.isPressed())  {
                if (player.isBlocking()) {
                    if (!player.getItemCooldownManager().isCoolingDown(player.getMainHandStack().getItem())) {
                        ClientPlayNetworking.send(PacketRegistry.SHIELD_BASH_PACKET, PacketByteBufs.create());
                    }
                }
            }
        });
    }
}
