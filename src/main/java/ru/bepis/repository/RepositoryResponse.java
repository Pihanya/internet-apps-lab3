package ru.bepis.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Wither;

@Wither
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryResponse<T> {
  private boolean success;
  private Exception exception;
  private T result;

  public static <T> RepositoryResponse<T> getSuccessResponseWith(T result) {
    return new RepositoryResponse<T>()
        .withSuccess(true)
        .withResult(result);
  }

  public static <T> RepositoryResponse<T> getFailResponseWith(Exception ex) {
    return new RepositoryResponse<T>()
        .withSuccess(false)
        .withException(ex);
  }

  public boolean isSuccess() {
    return success;
  }

  public boolean hasResult() {
    return result != null;
  }
}
