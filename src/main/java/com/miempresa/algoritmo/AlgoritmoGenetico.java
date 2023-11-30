package com.miempresa.algoritmo;

import com.miempresa.bean.ElectrodomesticoBean;
import com.miempresa.entidades.Electrodomestico;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AlgoritmoGenetico {

    public List<Electrodomestico> algoritmoGenetico(int capacidadMaximaCamion) {

        ElectrodomesticoBean electrodomesticoBean = new ElectrodomesticoBean();

        Poblacion poblacion = new Poblacion();
        List<Cromosoma> cromosomas = new ArrayList<>();

        for (int i = 0; i < 6; i++) {

            /*creamos una muestra de una colecion des genes, osea la primera fila*/
            List<Gen> genes = new ArrayList<>();

            electrodomesticoBean.obtenerElectrodomesticos();
            List<Electrodomestico> lista = electrodomesticoBean.obtenerElectrodomesticos();
            for (Electrodomestico electro : lista) {
                Gen gen = new Gen();
                gen.setElectrodomestico(electro);
                genes.add(gen);
            }

            Cromosoma cromosoma = new Cromosoma();
            cromosoma.setCromosoma(genes);

            cromosomas.add(cromosoma);

        }

        poblacion.setPoblacion(cromosomas);

        /*se supone que hasta aqui ya tengo toda la poblacion con los cromosomas dentro y lo genes tambien*/
        List<Cromosoma> poblacionPrueba = poblacion.getPoblacion();
        System.out.println("-----------------------------------------------------------------------");
        int tamCromosoma = poblacionPrueba.get(0).getCromosoma().size();
        /*debe mostrarme tamaño 50*/
        /*se muestra toda la poblacion con las activaciones de 1 o 0*/
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < tamCromosoma; j++) {
                int valor = poblacionPrueba.get(i).getCromosoma().get(j).getValor();
                System.out.print(valor + ",");
            }
            System.out.println();
        }

        /*Mostramos los valores de aquellos que estan activadas con 1, los demas son 0*/
        System.out.println("-----------------------------------------------------------------------");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < tamCromosoma; j++) {
                int valor = poblacionPrueba.get(i).getCromosoma().get(j).getValor();
                if (valor == 1) {
                    System.out.print(poblacionPrueba.get(i).getCromosoma().get(j).getProducto().getNombre() + ", \t");
                } else {
                    System.out.print("      " + valor + ", \t");

                }
            }
            System.out.println();
        }

        /*aqui empieza el ALGORITMO GENETICO*/
        /*DEBERIA HACER UNA IMPLEMENTACION DONDE las iteraciones se detengan cuando todos los fitness sean iguales*/
        /*PORQUE 200 tambien parece demacido, la poblacion se atrofia o se matan entre si, o mata la solucion que se dio en la generacion 50 por ejemplo*/
        for (int generacion = 0; generacion < 50; generacion++) {

            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX GENERACION NUMERO: " + (generacion + 1) + " XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx");
            System.out.println("----------------PESO " + capacidadMaximaCamion + "-------------------------------------------------------");
            /*Hacemos la penalizacion*/
            int capacidadMaxima = capacidadMaximaCamion;
            int sumatoriaDePesos = 0;
            int sumatoriaDeBeneficios = 0;
            int penalizacion = 0;
            int funcionFiteness = 0;

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < tamCromosoma; j++) {
                    int valor = poblacionPrueba.get(i).getCromosoma().get(j).getValor();
                    
                    if (valor == 1) {
                        
                        sumatoriaDePesos = sumatoriaDePesos + poblacionPrueba.get(i).getCromosoma().get(j).getProducto().getPesoKg().intValue();
                        sumatoriaDeBeneficios = sumatoriaDeBeneficios + poblacionPrueba.get(i).getCromosoma().get(j).getProducto().getBeneficio();
                        
                        if (sumatoriaDePesos > capacidadMaxima) {
                            /*hallar la penalizacion*/
                            penalizacion = sumatoriaDePesos - capacidadMaxima;

                        } else {
                            /*no hay penalizacion = 0*/
                            penalizacion = 0;
                        }
                    }
                }

                funcionFiteness = sumatoriaDeBeneficios - penalizacion;

                poblacionPrueba.get(i).setFitness(funcionFiteness);
                sumatoriaDePesos = 0;
                sumatoriaDeBeneficios = 0;

            }

            /*mostramos el fitness*/
            System.out.println("-----------------------------------------------------------------------");
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < tamCromosoma; j++) {
                    int valor = poblacionPrueba.get(i).getCromosoma().get(j).getValor();
                    System.out.print(valor + ",");
                }
                System.out.print(" Fit = " + poblacionPrueba.get(i).getFitness() + "\tz");
                System.out.println();
            }

            System.out.println("-----------------------------------------------------------------------");
            /*volvemos a signarle al mismo listadePolblacion la misma lista, pero esta vez ordenada*/
            poblacionPrueba = poblacionPrueba.stream().sorted(Comparator.comparingInt(Cromosoma::getFitness).reversed()).collect(Collectors.toList());
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < tamCromosoma; j++) {
                    int valor = poblacionPrueba.get(i).getCromosoma().get(j).getValor();
                    System.out.print(valor + ",");
                }
                System.out.print(" Fit = " + poblacionPrueba.get(i).getFitness() + "\tz");
                System.out.println();
            }

            /*Hacemos los cruzes con los mejores, osea los dos primero de la lisa de la poblacion*/
            System.out.println("-----------------------------------------------------------------------");
            Cromosoma mejor1 = poblacionPrueba.get(0);
            Cromosoma mejor2 = poblacionPrueba.get(1);

            /*Mostramos los dos nuevos hjos*/
            System.out.println("MEJORES CROMOSOMAS");
            mejor1.getCromosoma().stream().forEach(m1 -> System.out.print(m1.getValor() + ","));
            System.out.println();
            mejor2.getCromosoma().stream().forEach(m2 -> System.out.print(m2.getValor() + ","));

            /*Ejemplo, mostrar los peores de la poblacion*/
            Cromosoma peor1 = poblacionPrueba.get(5);
            Cromosoma peor2 = poblacionPrueba.get(4);

            System.out.println();
            System.out.println("PEORES CROMOSOMAS");
            peor1.getCromosoma().stream().forEach(p1 -> System.out.print(p1.getValor() + ","));
            System.out.println();
            peor2.getCromosoma().stream().forEach(p2 -> System.out.print(p2.getValor() + ","));

            /* aqui debo hacer los cruzes*/
            Cromosoma hijo1 = new Cromosoma();
            Cromosoma hijo2 = new Cromosoma();

            int mitad = tamCromosoma / 2;

            List<Gen> genes = new ArrayList<>();
            for (int i = 0; i < tamCromosoma; i++) {
                if (i < mitad) {
                    genes.add(mejor1.getCromosoma().get(i));
                } else {
                    genes.add(mejor2.getCromosoma().get(i));
                }

            }
            hijo1.setCromosoma(genes);

            /*Para el hijo 2 faltaria otro for*/
            List<Gen> genes1 = new ArrayList<>();
            for (int i = 0; i < tamCromosoma; i++) {
                if (i < mitad) {
                    genes1.add(mejor2.getCromosoma().get(i));
                } else {
                    genes1.add(mejor1.getCromosoma().get(i));
                }

            }
            hijo2.setCromosoma(genes1);

            /*falta probar hasta aqui*/
            System.out.println();
            System.out.println("Hijos CROMOSOMAS");
            hijo1.getCromosoma().stream().forEach(p1 -> System.out.print(p1.getValor() + ","));
            System.out.println();
            hijo2.getCromosoma().stream().forEach(p2 -> System.out.print(p2.getValor() + ","));

            System.out.println();
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("HIJOS INTRODUCIDOS");
            /*Solo me quueda reemplazar los hijos a los peores y mostrar*/
            poblacionPrueba.get(4).setCromosoma(hijo2.getCromosoma());
            poblacionPrueba.get(5).setCromosoma(hijo1.getCromosoma());

            /*ME PARECE TAMBIEN QUE NO ESTOY HACIENDO LA INSERCION DE LOS HIJOS DE MANERA ADECUADA*/
            /*Mostrar*/
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < tamCromosoma; j++) {
                    int valor = poblacionPrueba.get(i).getCromosoma().get(j).getValor();
                    System.out.print(valor + ",");
                }
                //System.out.print(" Fit = " + poblacionPrueba.get(i).getFitness() + "\tz");
                System.out.println();
            }

            
            System.out.println();
            System.out.println("-----------------------------------------------------------------------");
                if (generacion != 199) {
                System.out.println("MUTAMOS UN CROMOSOMA");
                
                int valorAletorioPoblacion = (int) (Math.random() * 6);
                int valorAleatorioCromosoma = (int) (Math.random() * tamCromosoma);
                int valorBinarioAleatorio = (Math.random() * 100 > 50) ? 1 : 0;

                System.out.println("Valor Aleatorio poblacion:" + valorAletorioPoblacion);
                System.out.println("Valor Aleatorio Cromosoma:" + valorAleatorioCromosoma);
                System.out.println("Valor Aleatorio Binario:" + valorBinarioAleatorio);

                /*ESTO NO FUNCIONA PORQUE AL MOMENTO DE MUTAR UN GEN, MUTA a TODOS DE LA COLUMNA DEL GEN*/
                /*SI HAGO ESTO, TODA LA LISTA SE MODIFICA CON EL VALOR DE LAS VARIABLES DECLARADOAS*/
                //poblacionPrueba.get(valorAletorioPoblacion).getCromosoma().get(valorAleatorioCromosoma).setValor(valorBinarioAleatorio);
                /*PARA CAMBIAR SOLAMENTE UN VAlo, DEBO DE IR SACANDO PASO POR PASO LOS OBJETOS*/
                //Hagamos la prueba trayendo el objeto de los valores aleatorios
                Gen genOriginal = poblacionPrueba.get(valorAletorioPoblacion).getCromosoma().get(valorAleatorioCromosoma);
                Gen genMutado = new Gen();
                genMutado.setElectrodomestico(genOriginal.getProducto());
                genMutado.setValor(valorBinarioAleatorio);
                poblacionPrueba.get(valorAletorioPoblacion).getCromosoma().set(valorAleatorioCromosoma, genMutado);

                /*TENGO INSERTAR CON EL VALOR CAMBIADO JUNTO CON EL OBJETO ELECTRODOMOESTICO ASOCIADO*/

            
                System.out.println("-----------------------------------------------------------------------");

                /*Mostrar*/
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < tamCromosoma; j++) {
                        int valor = poblacionPrueba.get(i).getCromosoma().get(j).getValor();
                        System.out.print(valor + ",");
                    }
                    //System.out.print(" Fit = " + poblacionPrueba.get(i).getFitness() + "\tz");
                    System.out.println();
                }
                
            }

        }

        System.out.println("--------------------RESULTADO-------------------------------");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < tamCromosoma; j++) {
                int valor = poblacionPrueba.get(i).getCromosoma().get(j).getValor();
                if (valor == 1) {
                    System.out.print(poblacionPrueba.get(i).getCromosoma().get(j).getProducto().getNombre() + ", \t");
                } else {
                    System.out.print("      " + valor + ", \t");

                }
            }
            System.out.println();
        }

        System.out.println("FIN DEL ALGORITMO BORRAR ESTE COMENTARIO");

        List<Electrodomestico> listaElecEnvio = new ArrayList<>();

        for (int i = 0; i < tamCromosoma; i++) {
            if (poblacionPrueba.get(1).getCromosoma().get(i).getValor() == 1) {

                Electrodomestico electrodomestico = poblacionPrueba.get(1).getCromosoma().get(i).getProducto();
                listaElecEnvio.add(electrodomestico);

            }

        }

        //listaElecEnvio.stream().forEach(a -> System.out.print(a.getNombre()));
        return listaElecEnvio;
        /*HASTA AQUI FUNCIONA*/

    }

}
