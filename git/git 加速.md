# Speed Up Git

## 设置代理

Git 仓库有以下两种形式：
HTTPS: `https://github.com/owner/repo.git`
SSH: `git@github.com:owner/repo.git`

### http

#### http 代理

```
git config --global http.proxy "http://127.0.0.1:1087"
git config --global https.proxy "http://127.0.0.1:1087"
```

#### socket5 代理

```
git config --global http.proxy "socks5://127.0.0.1:1086"
git config --global https.proxy "socks5://127.0.0.1:1086"
```

#### 取消代理

```
git config --global --unset http.proxy
git config --global --unset https.proxy
```

### ssh

修改 `~/.ssh/config` 文件。

```
Host github.com
  Hostname github.com
  User git
  # 走 HTTP 代理
  #ProxyCommand socat - PROXY:127.0.0.1:%h:%p,proxyport=1087
  # 走 socks5 代理
  ProxyCommand nc -v -x 127.0.0.1:1086 %h %p
```

## 共享 SSH 连接

利用 SSH 的 ControlPersist 特性，使多个 ssh 会话共享一个已经存在的连接，从而达到加速的目的。

修改 `~/.ssh/config` 文件。

```
Host github.com
  Hostname github.com
  User git
  # ssh key
  PubkeyAuthentication yes
  IdentityFile /Users/djk/.ssh/github_id_rsa
  # share ssh connection
  ControlMaster auto
  ControlPath ~/.ssh/%r@%h:%p
  ControlPersist yes
  # 走 HTTP 代理
  #ProxyCommand socat - PROXY:127.0.0.1:%h:%p,proxyport=1087
  # 走 socks5 代理
  ProxyCommand nc -v -x 127.0.0.1:1086 %h %p
```

* ControlMaster auto 可以使多个 ssh 会话共享一个已经存在的连接，如果没有，则自动创建一个连接。
* ControlPath 可以指定想要共享的连接。%r 代表远程登录用户名，一般都为 git，%h 表示目标主机，%p 表示端口。
* ControlPersist yes 则可以让共享的连接持有处于连接状态。
* Compression yes 为压缩选项，打开之后加快数据传输速度。
