package br.ufpe.vinicius.projectsniffer.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

@Service
public class CookieService {

    /**
     * Adiciona um cookie ao response usando a API Cookie.
     * Se você precisa de SameSite, use addCookieWithSameSite.
     *
     * @param response  HttpServletResponse
     * @param name      nome do cookie
     * @param value     valor do cookie (será url-encoded)
     * @param maxAgeSec tempo de vida em segundos (0 -> sessão, -1 -> indefinido dependente do container)
     * @param path      caminho (ex: "/")
     * @param domain    domínio (pode ser null)
     * @param secure    flag secure
     * @param httpOnly  flag HttpOnly
     */
    public void addCookie(HttpServletResponse response,
                          String name,
                          String value,
                          int maxAgeSec,
                          String path,
                          String domain,
                          boolean secure,
                          boolean httpOnly) {
        String encoded = URLEncoder.encode(value == null ? "" : value, StandardCharsets.UTF_8);
        Cookie cookie = new Cookie(name, encoded);
        cookie.setMaxAge(maxAgeSec);
        cookie.setPath(path == null ? "/" : path);
        if (domain != null && !domain.isBlank()) cookie.setDomain(domain);
        cookie.setSecure(secure);
        cookie.setHttpOnly(httpOnly);
        response.addCookie(cookie);
    }

    /**
     * Adiciona um cookie com suporte explícito a SameSite (Lax / Strict / None).
     * Constrói o header Set-Cookie manualmente para garantir suporte a SameSite.
     *
     * @param response  HttpServletResponse
     * @param name      nome do cookie
     * @param value     valor do cookie (será url-encoded)
     * @param maxAgeSec tempo de vida em segundos
     * @param path      caminho
     * @param domain    domínio (pode ser null)
     * @param secure    flag secure
     * @param httpOnly  flag httpOnly
     * @param sameSite  "Lax", "Strict", "None", ou null para omitir
     */
    public void addCookieWithSameSite(HttpServletResponse response,
                                      String name,
                                      String value,
                                      int maxAgeSec,
                                      String path,
                                      String domain,
                                      boolean secure,
                                      boolean httpOnly,
                                      String sameSite) {
        String encoded = URLEncoder.encode(value == null ? "" : value, StandardCharsets.UTF_8);
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("=").append(encoded);

        if (maxAgeSec >= 0) {
            sb.append("; Max-Age=").append(maxAgeSec);
            // opcional: adicionar Expires (alguns navegadores consultam Expires)
            // Instant expires = Instant.now().plusSeconds(maxAgeSec);
            // sb.append("; Expires=").append(DateTimeFormatter.RFC_1123_DATE_TIME.format(zonedExpires));
        }

        sb.append("; Path=").append(path == null ? "/" : path);

        if (domain != null && !domain.isBlank()) {
            sb.append("; Domain=").append(domain);
        }

        if (secure) sb.append("; Secure");
        if (httpOnly) sb.append("; HttpOnly");
        if (sameSite != null && !sameSite.isBlank()) {
            // sameSite deve ser "Lax", "Strict" ou "None"
            sb.append("; SameSite=").append(sameSite);
        }

        // Adiciona o header; múltiplos cookies são suportados (addHeader)
        response.addHeader("Set-Cookie", sb.toString());
    }

    /**
     * Lê o valor de um cookie do request. Retorna Optional.empty() se não existir.
     * O valor retornado é URL-decoded.
     */
    public Optional<String> getCookieValue(HttpServletRequest request, String name) {
        if (request == null || name == null) return Optional.empty();
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return Optional.empty();

        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .map(c -> {
                    try {
                        return java.net.URLDecoder.decode(c.getValue(), StandardCharsets.UTF_8);
                    } catch (Exception e) {
                        return c.getValue();
                    }
                });
    }

    /**
     * Remove (invalida) um cookie definindo Max-Age=0 e Path igual ao existente.
     * Alguns navegadores também verificam Domain, então passe domain se você definiu antes.
     */
    public void deleteCookie(HttpServletResponse response, String name, String path, String domain) {
        Cookie cookie = new Cookie(name, "");
        cookie.setPath(path == null ? "/" : path);
        cookie.setMaxAge(0); // instrui o navegador a remover
        if (domain != null && !domain.isBlank()) cookie.setDomain(domain);
        // geralmente remoção não precisa secure/httpOnly flags, mas manter como false é OK
        response.addCookie(cookie);
    }
}

