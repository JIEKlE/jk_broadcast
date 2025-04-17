package jiekie.command;

import jiekie.util.ChatUtil;
import jiekie.util.SoundUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            ChatUtil.notPlayer(sender);
            return true;
        }

        Player player = (Player) sender;
        if(!player.isOp()) {
            ChatUtil.notOp(player);
            return true;
        }

        if(args == null || args.length == 0) {
            ChatUtil.commandHelper(player);
            return true;
        }

        switch (args[0]) {
            case "채팅":
                broadCastByChat(player, args);
                break;

            case "타이틀":
                broadCastByTitle(player, args);
                break;

            case "테스트":
                broadCastTest(player, args);
                break;

            case "도움말":
                ChatUtil.commandList(player);
                break;

            default:
                ChatUtil.commandHelper(player);
                break;
        }

        return true;
    }

    /* 채팅 */
    private void broadCastByChat(Player player, String[] args) {
        if(args.length < 2) {
            player.sendMessage(ChatUtil.wrongCommand() + " (/공지사항 채팅 내용)");
            return;
        }

        String contents = getContents(args);

        broadcastBlank();
        Bukkit.broadcastMessage(getBroadcastPrefix() + contents);
        broadcastBlank();

        for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            SoundUtil.playNoteBlockBell(onlinePlayer);
        }
    }

    /* 타이틀 */
    private void broadCastByTitle(Player player, String[] args) {
        if(args.length < 2) {
            player.sendMessage(ChatUtil.wrongCommand() + " (/공지사항 타이틀 내용)");
            return;
        }

        String contents = getContents(args);
        for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.sendTitle(contents, "", 10, 100, 10);
            SoundUtil.playBeaconActivate(onlinePlayer);
        }
    }

    /* 테스트 */
    private void broadCastTest(Player player, String[] args) {
        if(args.length < 2) {
            player.sendMessage(ChatUtil.wrongCommand() + " (/공지사항 테스트 내용)");
            return;
        }

        String contents = getContents(args);
        player.sendMessage(getBroadcastPrefix() + contents);
        SoundUtil.playNoteBlockBell(player);
    }

    /* 공지 내용 조합 */
    private String getContents(String[] args) {
        StringBuffer sb = new StringBuffer();
        for(int i = 1 ; i < args.length ; i++) {
            if(i != 1)
                sb.append(" ");
            sb.append(args[i]);
        }

        String contents = sb.toString();
        return ChatColor.translateAlternateColorCodes('&', contents);
    }

    /* 공지 말머리 */
    private String getBroadcastPrefix() {
        return "[ " + ChatColor.RED + ChatColor.BOLD + "공 지" + ChatColor.RESET + " ] ";
    }

    /* 공백 추가 */
    private void broadcastBlank() {
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
    }
}
