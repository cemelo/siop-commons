/*
 * Copyright (C) 2014  Secretaria de Orçamento Federal
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package br.gov.siop.commons;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Esta enumeração representa o ambiente de execução atual do SIOP.
 *
 * O ambiente de execução do SIOP pode ser definido de duas formas:
 *
 *   - pela variável de ambiente `SIOP_ENV`
 *   - pela propriedade de sistema `siop.environment`
 *
 * Os valores suportados por padrão são: `DEVELOPMENT`, `TEST` e `PRODUCTION`.
 * Qualquer outro valor definido na variável de ambiente será representado pela
 * constante `OTHER`.
 *
 * Caso não seja definido nenhum valor de ambiente nas propriedades do sistema,
 * a constante `DEVELOPMENT` será utilizada.
 *
 * @author Carlos Eduardo Melo [[mailto:carlos.e.melo@planejamento.gov.br]]
 * @version 1.0
 * @since 1.0
 */
public enum Environment {

    /**
     * Representa o ambiente de desenvolvimento.
     */
    DEVELOPMENT,

    /**
     * Representa o ambiente de teste.
     */
    TEST,

    /**
     * Representa o ambiente de produção.
     */
    PRODUCTION,

    /**
     * Representa qualquer outro ambiente definido nas configurações.
     */
    OTHER;

    private static final Map<String, Environment> valuesMap;

    private static final String envName;

    static {
        Map<String, Environment> tempMap = new HashMap<String, Environment>();

        for (Environment env : values()) {
            tempMap.put(env.name(), env);
        }

        valuesMap = Collections.unmodifiableMap(tempMap);

        if (System.getenv().containsKey("SIOP_ENV")) {
            envName = System.getenv("SIOP_ENV").toUpperCase();
        } else {
            envName = System.getProperty("siop.environment", "development").toUpperCase();
        }
    }

    /**
     * Retorna a constante que representa o ambiente atual.
     *
     * @return A constante da enumeração que representa o ambiente atual.
     */
    public static Environment getCurrentEnv() {
        if (valuesMap.containsKey(getEnvName()))
            return valuesMap.get(getEnvName());

        return OTHER;
    }

    /**
     * Retorna o nome do ambiente conforme definido na propriedade do sistema.
     *
     * @return O nome do ambiente conforme definido na propriedade do sistema.
     */
    public static String getEnvName() {
        return envName;
    }
}
