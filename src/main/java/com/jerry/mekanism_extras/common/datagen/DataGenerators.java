//package com.jerry.mekanism_extras.common.datagen;
//
//import com.jerry.mekanism_extras.MekanismExtras;
//import net.minecraft.core.HolderLookup;
//import net.minecraft.data.DataGenerator;
//import net.minecraft.data.PackOutput;
//import net.minecraftforge.common.data.ExistingFileHelper;
//import net.minecraftforge.data.event.GatherDataEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//
//import java.util.concurrent.CompletableFuture;
//
//@Mod.EventBusSubscriber(modid = MekanismExtras.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
//public class DataGenerators {
//    @SubscribeEvent
//    public static void gatherData(GatherDataEvent event) {
//        DataGenerator generator = event.getGenerator();
//        PackOutput packOutput = generator.getPackOutput();
//        ExistingFileHelper helper = event.getExistingFileHelper();
//        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
//
//        generator.addProvider(event.includeServer(), new ExtraWorldGenProvider(packOutput, lookupProvider));
//    }
//}
