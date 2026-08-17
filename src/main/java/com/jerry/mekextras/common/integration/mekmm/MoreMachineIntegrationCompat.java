package com.jerry.mekextras.common.integration.mekmm;

import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;

/**
 * Compatibility boundary for Mekanism: MoreMachine integration.
 *
 * Mekanism Extras only provides extra-tier factories for the factory types it was
 * explicitly implemented for. New MoreMachine enum values must not be treated as
 * supported automatically just because MoreMachineFactoryType.values() contains them.
 */
public final class MoreMachineIntegrationCompat {

    public static final MoreMachineFactoryType[] SUPPORTED_FACTORY_TYPES = {
            MoreMachineFactoryType.RECYCLING,
            MoreMachineFactoryType.PLANTING_STATION,
            MoreMachineFactoryType.CNC_STAMPING,
            MoreMachineFactoryType.CNC_LATHING,
            MoreMachineFactoryType.CNC_ROLLING_MILL,
            MoreMachineFactoryType.REPLICATING
    };

    private MoreMachineIntegrationCompat() {}

    public static boolean isFactoryTypeSupported(MoreMachineFactoryType type) {
        return switch (type) {
            case RECYCLING, PLANTING_STATION, CNC_STAMPING, CNC_LATHING, CNC_ROLLING_MILL, REPLICATING -> true;
            default -> false;
        };
    }

    public static IllegalArgumentException unsupportedFactoryType(MoreMachineFactoryType type) {
        return new IllegalArgumentException("Unsupported Mekanism: MoreMachine factory type: " + type);
    }
}
