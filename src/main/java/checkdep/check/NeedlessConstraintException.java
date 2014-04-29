package checkdep.check;

/**
 * 不必要な制約.
 * 存在しない依存を制約として定義している場合は無駄なので知らせる.
 */
class NeedlessConstraintException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public NeedlessConstraintException(String message) {
    super(message);
  }
}
