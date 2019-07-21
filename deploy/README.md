Automated deployments.

## Getting started

Dependencies:

* ansible
* being an SoC student

Define `secrets.yml` for your über-secret stuff, for example
```yaml
secret_master_host: xcna0.comp.nus.edusg
secret_master_port: 1337
secret_token_command: ub3rs3cr1tanduseless
```
(you can actually call it anything you want, but I already made git ignore `/deploy/secrets.yml` so why not?)

## Deployment

To do the deployment we run these 2 playbooks

1. `setup.yml` which copies stuff over to master
2. `run-slaves.yml` which fires everything

which you run like this
```bash
ansible-playbook -i hosts/compute_cluster -e @secrets.yml PLAYBOOK.yml
```

## Warning

`run-slaves.yml` is a giant hack and that playbook is **NOT** idempotent!
In addition it only works with *my* SoC account and requires you install a recent version of tmux (which xcn nodes do not have).
