package de.jomlua.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class VoidGenerator extends ChunkGenerator {


    public List<BlockPopulator> getDefaultPopulators(World world) {
        return Arrays.asList();
    }

    public boolean canSpawn(World world, int x, int z) {
        return true;
    }

    public int xyzToByte(int x, int y, int z) {
        return (x * 16 + z) * 128 + y;
    }

    public byte[] generate(World world, Random rand, int chunkx, int chunkz) {
        byte[] result = new byte['耀'];
        if (chunkx == 0 && chunkz == 0) {
            result[this.xyzToByte(0, 64, 0)] = (byte) Material.BEDROCK.getId();
        }

        return result;
    }

    public Location getFixedSpawnLocation(World world, Random random) {
        return new Location(world, 0.0D, 66.0D, 0.0D);
    }
}
