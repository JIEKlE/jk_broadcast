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

        ChatUtil.broadcast(player, contents);
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
            onlinePlayer.sendTitle("\uA004", contents, 10, 100, 10);
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
        ChatUtil.broadcast(player, contents);
        SoundUtil.playNoteBlockBell(player);
    }

    /* 공지 내용 조합 */
    private String getContents(String[] args) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1 ; i < args.length ; i++) {
            if(i != 1)
                sb.append(" ");
            sb.append(args[i]);
        }

        String contents = sb.toString();
        return ChatColor.translateAlternateColorCodes('&', contents);
    }
}
