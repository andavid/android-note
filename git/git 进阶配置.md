# git 进阶配置

## [scmpuff](https://github.com/mroth/scmpuff)

scmpuff 是一个 git 拓展，支持数字快捷键的操作。尤其是当文件路径较长时，我们可以用数字来代替文件，同时也支持 1-3 这种写法，表示多个文件。

### Installation

```bash
brew install scmpuff
```

### Setup

由于 scmpuff 默认会添加一些别名，例如 gs 和 gd 等等。可以在初始化的时候添加 `--aliases=false` 取消添加别名。

在 ~/.zshrc 文件添加以下命令。

```bash
eval "$(scmpuff init -s --aliases=false)"
```

### Usage

最常用的命令就是 `scmpuff_status`，可以把它添加为别名 `gs`。

```bash
alias gs='scmpuff_status'
```

然后，就可以使用数字代替文件，例如 `git add 2`, `git checkout 1`, `git reset 2-4`。

## [icdiff](https://github.com/jeffkaufman/icdiff)

git 默认的对比是上下对比，但个人认为不够直观。尤其是在大屏显示器上，浪费了大量空间。

icdiff 是一个左右水平显示的 diff 工具。安装命令如下：

```bash
pip3 install git+https://github.com/jeffkaufman/icdiff.git
```

由于 oh-my-zsh 默认添加了 gd 别名，可以在 `.oh-my-zsh/plugins/git/git.plugin.zsh` 手动注释掉。

然后在 ~/.zshrc 文件添加一个 gd 函数，就可以使用 gd 来代替 git diff。


```bash
function gd() {
  params=`scmpuff expand "$@" 2>/dev/null`

  if [ $# -eq 0 ]; then
    git difftool --no-prompt --extcmd "icdiff --line-numbers --no-bold" | less -FRX
  elif [ ${#params} -eq 0 ]; then
    git difftool --no-prompt --extcmd "icdiff --line-numbers --no-bold" "$@" | less -FRX
  else
    git difftool --no-prompt --extcmd "icdiff --line-numbers --no-bold" "$params" | less -FRX
  fi
}
```
