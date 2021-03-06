package com.tterrag.k9.mappings;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.tterrag.k9.util.annotation.NonNull;

public interface MappingDatabase<@NonNull T extends Mapping> {
    
    MappingDatabase<T> reload() throws IOException, NoSuchVersionException;
    
    default Collection<T> lookup(String search) {
        return Arrays.stream(MappingType.values()).flatMap(type -> lookup(type, search).stream()).distinct().collect(Collectors.toList());
    }
    
    default Collection<T> lookup(MappingType type, String search) {
        return Arrays.stream(NameType.values()).flatMap(by -> lookup(by, type, search).stream()).distinct().collect(Collectors.toList());
    }

    Collection<T> lookup(NameType by, MappingType type);

    Collection<T> lookup(NameType by, MappingType type, String search);
    
    default List<T> lookupExact(MappingType type, String name) {
        return Arrays.stream(NameType.values()).flatMap(by -> lookupExact(by, type, name).stream()).distinct().collect(Collectors.toList());
    }
    
    List<T> lookupExact(NameType by, MappingType type, String name);
}
