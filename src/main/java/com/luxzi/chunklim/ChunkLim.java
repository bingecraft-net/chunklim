package com.luxzi.chunklim;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.server.ServerLifecycleHooks;

@Mod(ChunkLim.MODID)
public class ChunkLim
{
    public static final String MODID = "chunklim";
    private static final Logger LOGGER = LogUtils.getLogger();
    
    public ChunkLim()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        modEventBus.addListener(this::commonSetup);
        
        MinecraftForge.EVENT_BUS.register(this);
        
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SPEC, MODID + ".toml");
    }
    
    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("commonSetup complete");
    }
    
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("serverStart complete");
    }

    @SubscribeEvent
    public void loadChunk(ChunkEvent.Load event)
    {
        if (ServerLifecycleHooks.getCurrentServer().isReady() && event.isNewChunk())
        {
            LOGGER.info("Can't keep up, limiting chunk generation!");
        }
    }
}
