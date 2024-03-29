package apricot
package locators

/** Base trait for synthetic locators that locate content not on an actual file system.
  * 
  * This locator is useful for caches or special locators that construct a single file out of several
  * files located in other locators
  */
trait SyntheticLocator(systemPrefix: Path) extends ResourceManager.ResourceLocator {
  def locate(path: Path): Option[Resource] =
    if path.segments.startsWith(systemPrefix.segments) then locateSynth(path)
    else None

  def locateSynth(path: Path): Option[Resource]
}
