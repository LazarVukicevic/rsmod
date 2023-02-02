package org.rsmod.plugins.api.cache.type.enums

import org.rsmod.plugins.api.cache.type.ConfigType

public data class EnumType<K, V>(
    public override val id: Int,
    public val keyType: EnumTypeIdentifier,
    public val valType: EnumTypeIdentifier,
    public val default: V?,
    private val properties: MutableMap<K, V>
) : ConfigType, Iterable<Map.Entry<K, V>> {

    public val size: Int get() = properties.size
    public val isEmpty: Boolean get() = properties.isEmpty()
    public val isNotEmpty: Boolean get() = properties.isNotEmpty()

    public fun containsKey(key: K): Boolean = properties.containsKey(key)

    public operator fun get(key: K): V? {
        return properties[key] ?: default
    }

    override fun iterator(): Iterator<Map.Entry<K, V>> {
        return properties.iterator()
    }
}
