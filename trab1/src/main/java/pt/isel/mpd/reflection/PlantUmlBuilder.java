package pt.isel.mpd.reflection;

import java.io.*;

import java.lang.reflect.Member;
import java.util.function.Predicate;
import java.util.function.ToDoubleBiFunction;

import static java.lang.reflect.Modifier.isProtected;
import static pt.isel.mpd.utils.Utils.TODO;


public class PlantUmlBuilder {


    /**
     * Constrói uma instância que vai emitir os tipos via o writer
     * recebido na construção
     * @param writer
     */
    public PlantUmlBuilder(Writer writer) {
        TODO();
    }



    /**
     * @param fileName
     */
    public PlantUmlBuilder(String fileName)  {
        TODO();
    }
    


    /**
     * Adiciona, ao conjunto de tipos a processar, os tipos (classes ou interfaces), dos representantes
     * presentes no array "types". Pode ser invocados mais do que uma vez
     * @param types
     */
    public PlantUmlBuilder addTypes(Class<?> ... types) {
        TODO();
        return null;
    }

    
    /**
     * Define um filtro (predicado) para seleccionar os membros (
     * construtores, métodos e campos) a emitir na descrição PlantUml
     * @param filter
     */
    public PlantUmlBuilder memberFilter(Predicate<Member> filter ) {
        TODO();
        return this;
    }
    
    /**
     * Define um filtro (predicado) para selecionar os tipos a emitir
     * no ficheiro PlantUml
     * @param filter
     */
    public PlantUmlBuilder typeFilter(Predicate<Class<?>> filter ) {
        TODO();
        return this;
    }


    /**
     * Apresenta as super classes diretas ou indiretas dos classes especificadas
     * em "addTypes"
     * @return
     */
    public PlantUmlBuilder emmitSuperClasses() {
        TODO();
        return null;
    }

    /**
     * Apresenta as associados entre campos e outras classes (
     * (1 para 1) se forem referêsnicas ou de 1 para N se forem arrays
     * ou colecções
     * @return
     */
    public PlantUmlBuilder emmitFieldAssociations() {
        TODO();
        return null;
    }

    /**
     * Apresenta as interfaces implementadas direta ou indiretamente pelos tipos
     * especificados em "addTypes", bem como as respetivas relações de implementação
     * @return
     */
    public PlantUmlBuilder emmitImplementedInterfaces() {
        TODO();
        return null;
    }

    /**
     * Indicação o início da emissão no writer/ficheiro indicado na construção
     * da instância PlantUmlBuilder
     * A partir desta chamada já não podem ser efectuadas chamadas para
     * adicionar novos tipos ou definir filtros de membro ou de tipo
     * @return
     */
    public void build() {
        TODO();
    }
}
